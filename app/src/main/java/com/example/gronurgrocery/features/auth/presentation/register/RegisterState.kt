package com.example.gronurgrocery.features.auth.presentation.register

data class RegisterState(
    val emailText: String = "",
    val passwordText: String = "",
    val confirmPasswordText: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false
)
