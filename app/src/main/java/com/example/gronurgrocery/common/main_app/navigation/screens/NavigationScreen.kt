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
data class Login(
    val emailText: String = "",
    val passwordText: String = ""
)

@Serializable
object ForgotPassword

@Serializable
data class Verification(
    val emailText: String
)

@Serializable
data class ResetPassword(
    val emailText: String
)

@Serializable
data class SetUpAccount(
    val emailText: String = "",
    val password: String = ""
)