package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?
)