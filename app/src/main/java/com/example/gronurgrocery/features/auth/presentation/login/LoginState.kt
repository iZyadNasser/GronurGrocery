package com.example.gronurgrocery.features.auth.presentation.login

data class LoginState(
    val emailText: String = "",
    val passwordText: String = "",
    val isSavePasswordChecked: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false
)
