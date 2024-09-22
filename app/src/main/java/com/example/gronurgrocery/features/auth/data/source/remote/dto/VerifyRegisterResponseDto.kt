package com.example.gronurgrocery.features.auth.data.source.remote.dto


import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterResponse
import com.google.gson.annotations.SerializedName

data class VerifyRegisterResponseDto(
    @SerializedName("status")
    val status: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: Any?,
    @SerializedName("authorisation")
    val authorisation: Any?
)

fun VerifyRegisterResponseDto.toDomain(): VerifyRegisterResponse {
    return VerifyRegisterResponse(
        msg = message ?: ""
    )
}