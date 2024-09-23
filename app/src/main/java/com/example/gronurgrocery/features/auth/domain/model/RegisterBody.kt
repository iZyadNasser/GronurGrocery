package com.example.gronurgrocery.features.auth.domain.model

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    val email: String,
    val password: String,
    @SerializedName("password_confirmation")
    val passwordConfirmation: String
)