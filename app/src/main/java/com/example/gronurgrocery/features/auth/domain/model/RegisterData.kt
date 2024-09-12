package com.example.gronurgrocery.features.auth.domain.model

data class RegisterData(
    val emailText: String = "",
    val passwordText: String = "",
    val confirmPasswordText: String = ""
)