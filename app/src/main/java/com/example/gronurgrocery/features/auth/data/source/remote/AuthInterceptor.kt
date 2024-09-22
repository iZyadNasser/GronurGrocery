package com.example.gronurgrocery.features.auth.data.source.remote

import android.content.Context
import android.util.Log
import com.example.gronurgrocery.common.domain.use_cases.ReadUserTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val readUserTokenUseCase: ReadUserTokenUseCase
) : Interceptor {
    private var token: String = ""

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            token = readUserTokenUseCase().first() ?: ""

            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }


    }
}
