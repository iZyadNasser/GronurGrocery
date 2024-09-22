package com.example.gronurgrocery.features.auth.domain.use_case

import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterBody
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterResponse
import com.example.gronurgrocery.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VerifyRegisterEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        otp: String,
        email: String
    ): Flow<Resource<VerifyRegisterResponse>> {
        return authRepository.verifyEmail(
            verifyRegisterBody = VerifyRegisterBody(
                otp = otp,
                email = email
            )
        )
    }
}