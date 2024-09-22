package com.example.gronurgrocery.features.auth.data.source.remote

import com.example.gronurgrocery.features.auth.data.source.remote.dto.LoginResponseDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.RegisterResponseDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.VerifyRegisterResponseDto
import com.example.gronurgrocery.features.auth.domain.model.LoginBody
import com.example.gronurgrocery.features.auth.domain.model.RegisterBody
import com.example.gronurgrocery.features.auth.domain.model.ResendOTPBody
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {

    @POST("auth/register")
    suspend fun registerUser(@Body registerBody: RegisterBody): RegisterResponseDto

    @POST("auth/login")
    suspend fun loginUser(@Body loginBody: LoginBody): LoginResponseDto

    @POST("auth/verify")
    suspend fun verifyEmail(@Body verifyRegisterBody: VerifyRegisterBody): VerifyRegisterResponseDto

    @POST("auth/resendOTP")
    suspend fun resendRegisterVerify(
        @Body resendOTPBody: ResendOTPBody
    ): VerifyRegisterResponseDto
}