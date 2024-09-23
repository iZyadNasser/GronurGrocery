package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class AuthorisationLogin(
    @SerializedName("token")
    val token: String?,
    @SerializedName("type")
    val type: String?
)