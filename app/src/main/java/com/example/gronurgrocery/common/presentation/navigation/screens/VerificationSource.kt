package com.example.gronurgrocery.common.presentation.navigation.screens

sealed class VerificationSource(val source: String) {

    data object RegisterSource : VerificationSource("register_source")
    data object ForgotPasswordSource : VerificationSource("forgot_password_source")
}