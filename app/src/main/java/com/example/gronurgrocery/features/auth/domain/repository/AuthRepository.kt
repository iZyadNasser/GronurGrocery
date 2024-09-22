package com.example.gronurgrocery.features.auth.domain.repository

import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.model.LoginBody
import com.example.gronurgrocery.features.auth.domain.model.LoginResponse
import com.example.gronurgrocery.features.auth.domain.model.RegisterBody
import com.example.gronurgrocery.features.auth.domain.model.RegisterResponse
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterBody
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun sendRegister(
        registerBody: RegisterBody
    ): Flow<Resource<RegisterResponse>>

    suspend fun sendLogin(
        loginBody: LoginBody
    ): Flow<Resource<LoginResponse>>

    suspend fun verifyEmail(
        verifyRegisterBody: VerifyRegisterBody
    ): Flow<Resource<VerifyRegisterResponse>>

//    suspend fun setUpAccount(
//        fullName: String,
//        phone: String,
//        password: String
//    ): SetUpAccountResponseDto
//
//    suspend fun sendForgotPassword(
//        email: String
//    ): ForgotPasswordResponseDto
//
//    suspend fun verifyForgotPassword(
//        code: String
//    ): ForgotPasswordVerificationDto
//
//    suspend fun resetNewPassword(
//        password: String
//    ): ResetNewPasswordResponseDto

}