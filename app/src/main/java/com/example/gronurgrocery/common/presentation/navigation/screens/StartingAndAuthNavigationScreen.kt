package com.example.gronurgrocery.common.presentation.navigation.screens

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
    val emailText: String = "",
    val password: String = "",
    val source: String,
    val token: String = ""
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