package com.example.gronurgrocery.common.di

import com.example.gronurgrocery.common.Constants
import com.example.gronurgrocery.common.domain.use_cases.ReadUserTokenUseCase
import com.example.gronurgrocery.features.auth.data.source.remote.AuthInterceptor
import com.example.gronurgrocery.features.auth.data.source.remote.AuthService
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
object AppModule {

    @Provides
    fun provideAuthApiInterceptor(readUserTokenUseCase: ReadUserTokenUseCase): AuthInterceptor {
        return AuthInterceptor(readUserTokenUseCase)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(authInterceptor: AuthInterceptor): AuthService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Or choose a different level
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

}