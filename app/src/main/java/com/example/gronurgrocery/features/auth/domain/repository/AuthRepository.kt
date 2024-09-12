package com.example.gronurgrocery.features.auth.domain.repository

import com.example.gronurgrocery.features.auth.data.source.remote.dto.RegisterResponseDto

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
    )
}