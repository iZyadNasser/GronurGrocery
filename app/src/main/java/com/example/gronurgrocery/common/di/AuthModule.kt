package com.example.gronurgrocery.common.di

import com.example.gronurgrocery.common.Constants
import com.example.gronurgrocery.common.domain.repository.DataStoreRepository
import com.example.gronurgrocery.common.domain.use_cases.ReadUserTokenUseCase
import com.example.gronurgrocery.features.auth.data.repository.AuthRepositoryImpl
import com.example.gronurgrocery.features.auth.data.source.remote.AuthInterceptor
import com.example.gronurgrocery.features.auth.data.source.remote.AuthService
import com.example.gronurgrocery.features.auth.domain.repository.AuthRepository
import com.example.gronurgrocery.features.auth.domain.use_case.RegisterUserUseCase
import com.example.gronurgrocery.features.auth.domain.use_case.ResendEmailUseCase
import com.example.gronurgrocery.features.auth.domain.use_case.SaveUserTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: AuthService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(authRepository: AuthRepository): RegisterUserUseCase {
        return RegisterUserUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserTokenUseCase(dataStoreRepository: DataStoreRepository): SaveUserTokenUseCase {
        return SaveUserTokenUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideReadUserTokenUseCase(dataStoreRepository: DataStoreRepository): ReadUserTokenUseCase {
        return ReadUserTokenUseCase(dataStoreRepository)
    }

    @Provides
    @Singleton
    fun provideResendEmailUseCase(authRepository: AuthRepository): ResendEmailUseCase {
        return ResendEmailUseCase(authRepository)
    }
}