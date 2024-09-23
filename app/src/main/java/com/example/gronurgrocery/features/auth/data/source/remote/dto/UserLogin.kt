package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.google.gson.annotations.SerializedName

data class UserLogin(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @SerializedName("id")
val id: Int,
    @SerializedName("lang")
    val lang: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_num")
    val phoneNum: String,
    @SerializedName("social_id")
    val socialId: Any?,
    @SerializedName("social_type")
    val socialType: Any?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("user_name")
    val userName: Any?
)