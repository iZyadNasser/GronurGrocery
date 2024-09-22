package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.example.gronurgrocery.features.auth.domain.model.LoginResponse
import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("authorisation")
    val authorisation: AuthorisationLogin?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("user")
    val user: UserLogin
)

fun LoginResponseDto.toDomain(): LoginResponse {
    return LoginResponse(
        name = user.name,
        email = user.email,
        phoneNum = user.phoneNum,
        token = authorisation?.token ?: "",
        id = user.id
    )
}