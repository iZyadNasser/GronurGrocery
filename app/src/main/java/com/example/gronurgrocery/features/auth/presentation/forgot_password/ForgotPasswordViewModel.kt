package com.example.gronurgrocery.features.auth.presentation.forgot_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.auth.presentation.common.validEmail

class ForgotPasswordViewModel: ViewModel() {
    private val _state = mutableStateOf(ForgotPasswordState())
    val state: State<ForgotPasswordState> = _state

    fun updateEmailState(newEmail: String) {
        _state.value = _state.value.copy(
            emailText = newEmail,
            emailError = validEmail(newEmail)
        )
    }
}