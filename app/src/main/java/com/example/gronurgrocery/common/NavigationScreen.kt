package com.example.gronurgrocery.common

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen("splash_screen")
    data object Onboarding : NavigationScreen("onboarding_screen")
    data object Login : NavigationScreen("login_screen")
}