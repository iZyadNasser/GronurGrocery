package com.example.gronurgrocery.features.auth.presentation.set_up_account

data class SetUpAccountState(
    val fullNameText: String = "",
    val emailText: String = "",
    val phoneNumberText: String = "",
    val passwordText: String = "",
    val confirmPasswordText: String = "",
    val fullNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false
)
