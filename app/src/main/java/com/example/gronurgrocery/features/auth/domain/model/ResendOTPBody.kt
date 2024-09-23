package com.example.gronurgrocery.features.auth.domain.model

import com.google.gson.annotations.SerializedName

data class ResendOTPBody(
    @SerializedName("email")
    val email: String
)