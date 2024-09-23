package com.example.gronurgrocery.features.auth.domain.model

data class VerifyRegisterBody(
    val otp: String,
    val email: String
)
