package com.example.gronurgrocery.features.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.use_case.LoginUseCase
import com.example.gronurgrocery.features.auth.domain.use_case.SaveUserTokenUseCase
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus
import com.example.gronurgrocery.features.auth.presentation.common.validEmail
import com.example.gronurgrocery.features.auth.presentation.common.validPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase
): ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private var isFirst = true

    fun updateEmailState(newEmail: String) {
        _state.value = _state.value.copy(
            emailText = newEmail,
            emailError = validEmail(newEmail)
        )
    }

    fun updatePasswordState(newPassword: String) {
        _state.value = _state.value.copy(
            passwordText = newPassword,
            passwordError = validPassword(newPassword)
        )
    }

    fun togglePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    fun toggleSavePasswordCheckbox() {
        _state.value = _state.value.copy(
            isSavePasswordChecked = !_state.value.isSavePasswordChecked
        )
    }

    fun initializeState(email: String, password: String) {
        if (isFirst) {
            updateEmailState(email)
            updatePasswordState(password)
            isFirst = false
        }
    }

    fun endLoginProcess() {
        _state.value = _state.value.copy(
            loginStatus = ResponseStatus.NONE,
            loginErrorMsg = ""
        )
    }

    fun allValid(): Boolean {
        with(_state.value) {
            return ((emailError == null && passwordError == null) && (emailText.isNotBlank() && passwordText.isNotBlank()))
        }
    }

    fun sendLogin() {
        viewModelScope.launch {
            loginUseCase(
                email = _state.value.emailText,
                password = _state.value.passwordText
            ).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            loginStatus = ResponseStatus.SUCCESS
                        )
                        saveUserToken(result.data?.token ?: "")
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            loginStatus = ResponseStatus.FAILURE,
                            loginErrorMsg = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            loginStatus = ResponseStatus.LOADING
                        )
                    }
                }
            }
        }
    }

    private fun saveUserToken(token: String) {
        viewModelScope.launch {
            saveUserTokenUseCase(token)
        }
    }
}