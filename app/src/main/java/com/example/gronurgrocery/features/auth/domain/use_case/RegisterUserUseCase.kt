package com.example.gronurgrocery.features.auth.domain.use_case

import android.util.Log
import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.model.RegisterBody
import com.example.gronurgrocery.features.auth.domain.model.RegisterResponse
import com.example.gronurgrocery.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        registerBody: RegisterBody
    ): Flow<Resource<RegisterResponse>> {
        Log.e("j", "usecase", )
        return authRepository.sendRegister(registerBody)
    }

}