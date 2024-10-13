package com.example.gronurgrocery.features.auth.domain.model

data class RegisterResponse(
    val status: String?,
    val email: String?,
    val id: Int?,
    val token: String?,
    val message: String?
)