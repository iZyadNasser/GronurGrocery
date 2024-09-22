package com.example.gronurgrocery.features.auth.data.repository

import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.data.source.remote.AuthService
import com.example.gronurgrocery.features.auth.data.source.remote.dto.toDomain
import com.example.gronurgrocery.features.auth.domain.model.LoginBody
import com.example.gronurgrocery.features.auth.domain.model.LoginResponse
import com.example.gronurgrocery.features.auth.domain.model.RegisterBody
import com.example.gronurgrocery.features.auth.domain.model.RegisterResponse
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterBody
import com.example.gronurgrocery.features.auth.domain.model.VerifyRegisterResponse
import com.example.gronurgrocery.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthService
) : AuthRepository {

    override suspend fun sendRegister(
        registerBody: RegisterBody
    ): Flow<Resource<RegisterResponse>> = flow {
        try {
            emit(Resource.Loading())
            val registerResponse = apiService.registerUser(registerBody).toDomain()
            emit(Resource.Success(registerResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, please check your internet connection"))
        }
    }

    override suspend fun sendLogin(loginBody: LoginBody): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val loginResponse = apiService.loginUser(loginBody).toDomain()
            emit(Resource.Success(loginResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, please check your internet connection"))
        }
    }

    override suspend fun verifyEmail(verifyRegisterBody: VerifyRegisterBody): Flow<Resource<VerifyRegisterResponse>> = flow {
        try {
            emit(Resource.Loading())
            val verifyRegisterResponse = apiService.verifyEmail(verifyRegisterBody).toDomain()
            emit(Resource.Success(verifyRegisterResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, please check your internet connection"))
        }
    }
}