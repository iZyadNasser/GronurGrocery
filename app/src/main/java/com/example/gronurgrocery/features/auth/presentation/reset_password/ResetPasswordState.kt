package com.example.gronurgrocery.features.auth.presentation.reset_password

data class ResetPasswordState(
    val passwordText: String = "",
    val confirmPasswordText: String = "",
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false
)
