package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class AuthorisationRegister(
    @SerializedName("token")
    val token: String?,
    @SerializedName("type")
    val type: String?
)