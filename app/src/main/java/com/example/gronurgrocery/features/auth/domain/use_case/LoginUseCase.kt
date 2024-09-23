package com.example.gronurgrocery.features.auth.domain.use_case

import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.model.LoginBody
import com.example.gronurgrocery.features.auth.domain.model.LoginResponse
import com.example.gronurgrocery.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<LoginResponse>> {
        return authRepository.sendLogin(
            LoginBody(
                email = email,
                password = password
            )
        )
    }
}