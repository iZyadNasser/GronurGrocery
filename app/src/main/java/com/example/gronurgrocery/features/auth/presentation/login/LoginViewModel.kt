package com.example.gronurgrocery.features.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.auth.presentation.common.validEmail
import com.example.gronurgrocery.features.auth.presentation.common.validPassword

class LoginViewModel: ViewModel() {
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
}