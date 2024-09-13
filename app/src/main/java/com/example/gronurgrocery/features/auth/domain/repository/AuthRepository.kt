package com.example.gronurgrocery.features.auth.domain.repository

import com.example.gronurgrocery.features.auth.data.source.remote.dto.EmailVerificationDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.ForgotPasswordResponseDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.ForgotPasswordVerificationDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.LoginResponseDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.RegisterResponseDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.ResetNewPasswordResponseDto
import com.example.gronurgrocery.features.auth.data.source.remote.dto.SetUpAccountResponseDto

interface AuthRepository {

    suspend fun sendRegister(
        email: String,
        password: String,
        imei: String,
        token: String
    ): RegisterResponseDto

    suspend fun sendLogin(
        email: String,
        password: String,
        imei: String,
        token: String
    ): LoginResponseDto

    suspend fun verifyEmail(
        code: String
    ): EmailVerificationDto

    suspend fun setUpAccount(
        fullName: String,
        phone: String,
        password: String
    ): SetUpAccountResponseDto

    suspend fun sendForgotPassword(
        email: String
    ): ForgotPasswordResponseDto

    suspend fun verifyForgotPassword(
        code: String
    ): ForgotPasswordVerificationDto

    suspend fun resetNewPassword(
        password: String
    ): ResetNewPasswordResponseDto

}