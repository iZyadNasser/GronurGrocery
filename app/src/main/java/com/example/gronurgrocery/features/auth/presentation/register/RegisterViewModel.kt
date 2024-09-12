package com.example.gronurgrocery.features.auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.auth.presentation.common.validConfirmPassword
import com.example.gronurgrocery.features.auth.presentation.common.validEmail
import com.example.gronurgrocery.features.auth.presentation.common.validPassword

class RegisterViewModel : ViewModel() {

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
            confirmPasswordError = validConfirmPassword(newPassword, _state.value.confirmPasswordText),
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

}