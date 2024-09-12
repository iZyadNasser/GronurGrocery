package com.example.gronurgrocery.common.main_app.navigation.screens

import com.example.gronurgrocery.features.auth.domain.model.RegisterData
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Onboarding

@Serializable
object Register

@Serializable
object Login

@Serializable
object ForgotPassword

@Serializable
object Verification

@Serializable
object ResetPassword

@Serializable
data class SetUpAccount(
    val emailText: String = "",
    val password: String = ""
)