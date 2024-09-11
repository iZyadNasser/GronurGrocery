package com.example.gronurgrocery.common.main_app

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen("splash_screen")
    data object Onboarding : NavigationScreen("onboarding_screen")
    data object Register : NavigationScreen("register_screen")
    data object Login : NavigationScreen("login_screen")
    data object ForgotPassword : NavigationScreen("forgot_password_screen")
}