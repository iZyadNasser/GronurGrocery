package com.example.gronurgrocery.features.auth.presentation.login

import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus

data class LoginState(
    val emailText: String = "",
    val passwordText: String = "",
    val isSavePasswordChecked: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val loginStatus: ResponseStatus = ResponseStatus.NONE,
    val loginErrorMsg: String? = ""
)
