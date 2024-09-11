package com.example.gronurgrocery.common.main_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gronurgrocery.features.auth.presentation.ForgotPasswordScreen
import com.example.gronurgrocery.features.auth.presentation.LoginScreen
import com.example.gronurgrocery.features.auth.presentation.RegisterScreen
import com.example.gronurgrocery.features.starting.presentation.onboarding.OnboardingPager
import com.example.gronurgrocery.features.starting.presentation.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val SPLASH_DELAY_TIME = 2000L

@Composable
fun MyApp(
    navController: NavHostController,
    activity: MainActivity,
    viewModel: MainAppViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Splash.route
    ) {
        composable(route = NavigationScreen.Splash.route) {
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(key1 = true) {
                coroutineScope.launch {
                    delay(SPLASH_DELAY_TIME)
                    if (viewModel.onboardingState.value) {
                        navController.navigate(route = NavigationScreen.Register.route) {
                            popUpTo(route = NavigationScreen.Splash.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(route = NavigationScreen.Onboarding.route) {
                            popUpTo(route = NavigationScreen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            SplashScreen()
        }

        composable(route = NavigationScreen.Onboarding.route) {
            OnboardingPager(
                onBackPressed = { navController.popBackStack() },
                onLastContinuePressed = {
                    navController.navigate(route = NavigationScreen.Register.route) {
                        popUpTo(route = NavigationScreen.Onboarding.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = NavigationScreen.Register.route) {
            RegisterScreen(
                onSignInClick = {
                    navController.navigate(route = NavigationScreen.Login.route)
                },
                onUpButtonPressed = { activity.finish() }
            )
        }

        composable(route = NavigationScreen.Login.route) {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(route = NavigationScreen.Register.route) {
                        popUpTo(route = NavigationScreen.Register.route) {
                            inclusive = true
                        }
                    }
                },
                onUpButtonPressed = { navController.navigateUp() },
                onForgotPasswordClick = { navController.navigate(NavigationScreen.ForgotPassword.route) }
            )
        }

        composable(route = NavigationScreen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onUpButtonPressed = { navController.navigateUp() }
            )
        }
    }
}