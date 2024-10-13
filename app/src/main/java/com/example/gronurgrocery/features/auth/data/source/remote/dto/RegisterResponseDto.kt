package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.example.gronurgrocery.features.auth.domain.model.RegisterResponse
import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @SerializedName("authorization")
    val authorizationRegister: AuthorisationRegister?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("user")
    val userRegister: UserRegister?
)

fun RegisterResponseDto.toDomain() : RegisterResponse {
    return RegisterResponse(
        status = status,
        email = userRegister?.email,
        id = userRegister?.id,
        token = authorizationRegister?.token,
        message = message
    )
}