package com.example.gronurgrocery.features.auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.auth.presentation.validConfirmPassword
import com.example.gronurgrocery.features.auth.presentation.validEmail
import com.example.gronurgrocery.features.auth.presentation.validPassword

class RegisterViewModel : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

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

    fun updateConfirmPasswordState(newPassword: String) {
        _state.value = _state.value.copy(
            confirmPasswordText = newPassword,
            confirmPasswordError = validConfirmPassword(_state.value.passwordText, newPassword)
        )
    }

}