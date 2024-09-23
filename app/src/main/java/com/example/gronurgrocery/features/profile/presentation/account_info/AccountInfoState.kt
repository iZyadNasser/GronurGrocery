package com.example.gronurgrocery.features.profile.presentation.account_info

data class AccountInfoState(
    val nameText: String = "",
    val usernameText: String = "",
    val emailText: String = "",
    val phoneNumText: String = "",
    val nameError: String? = null,
    val usernameError: String? = null,
    val emailError: String? = null,
    val phoneNumError: String? = null
)