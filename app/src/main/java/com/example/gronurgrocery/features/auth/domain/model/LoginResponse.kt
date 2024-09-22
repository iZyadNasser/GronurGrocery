package com.example.gronurgrocery.features.auth.domain.model

data class LoginResponse(
    val name: String,
    val email: String,
    val phoneNum: String,
    val token: String,
    val id: Int,
)