package com.example.gronurgrocery.common.main_app

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen("splash_screen")
    data object Onboarding : NavigationScreen("onboarding_screen")
    data object Register : NavigationScreen("register_screen")
    data object Login : NavigationScreen("login_screen")
    data object ForgotPassword : NavigationScreen("forgot_password_screen")
    data object Verification: NavigationScreen("verification_screen")
    data object ResetPassword: NavigationScreen("reset_password_screen")
    data object SetUpAccount: NavigationScreen("set_up_account_screen")
}