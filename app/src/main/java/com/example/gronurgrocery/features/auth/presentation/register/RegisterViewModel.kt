package com.example.gronurgrocery.features.auth.presentation.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.model.RegisterBody
import com.example.gronurgrocery.features.auth.domain.use_case.RegisterUserUseCase
import com.example.gronurgrocery.features.auth.domain.use_case.SaveUserTokenUseCase
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus
import com.example.gronurgrocery.features.auth.presentation.common.validConfirmPassword
import com.example.gronurgrocery.features.auth.presentation.common.validEmail
import com.example.gronurgrocery.features.auth.presentation.common.validPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun updateEmailState(newEmail: String) {
        _state.value = _state.value.copy(
            emailText = newEmail,
            emailError = validEmail(newEmail),
        )
    }

    fun updatePasswordState(newPassword: String) {
        _state.value = _state.value.copy(
            passwordText = newPassword,
            passwordError = validPassword(newPassword),
            confirmPasswordError = validConfirmPassword(newPassword, _state.value.confirmPasswordText)
        )
    }

    fun updateConfirmPasswordState(newPassword: String) {
        _state.value = _state.value.copy(
            confirmPasswordText = newPassword,
            confirmPasswordError = validConfirmPassword(_state.value.passwordText, newPassword),
            anyError = _state.value.confirmPasswordError != null || _state.value.anyError
        )
    }

    fun togglePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    fun toggleConfirmPasswordVisibility() {
        _state.value = _state.value.copy(
            isConfirmPasswordVisible = !_state.value.isConfirmPasswordVisible
        )
    }

    fun allDataValid(): Boolean {
        with(_state.value) {
            return ((emailError == null && passwordError == null && confirmPasswordError == null) && (emailText.isNotBlank() && passwordText.isNotBlank() && confirmPasswordText.isNotBlank()))
        }
    }

    fun sendRegisterData() {
        viewModelScope.launch {
            val registerBody = RegisterBody(
                email = _state.value.emailText,
                password = _state.value.passwordText,
                passwordConfirmation = _state.value.confirmPasswordText
            )
            registerUserUseCase(
                registerBody = registerBody
            ).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            responseStatus = ResponseStatus.SUCCESS
                        )
                        Log.e("TAG", "sendRegisterData: ${result.data?.token ?: "null"}", )
                        saveUserToken(result.data?.token ?: "")
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            responseStatus = ResponseStatus.FAILURE,
                            registerErrorMsg = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            responseStatus = ResponseStatus.LOADING
                        )
                    }
                }
            }
        }
    }

    fun endRegisterProcess() {
        _state.value = _state.value.copy(
            responseStatus = ResponseStatus.NONE
        )
    }

    private fun saveUserToken(
        token: String
    ) {
        viewModelScope.launch {
            saveUserTokenUseCase(token)
        }
    }
}