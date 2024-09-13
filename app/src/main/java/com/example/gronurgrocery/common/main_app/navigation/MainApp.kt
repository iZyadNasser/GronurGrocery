package com.example.gronurgrocery.common.main_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.gronurgrocery.common.main_app.MainActivity
import com.example.gronurgrocery.common.main_app.navigation.screens.ForgotPassword
import com.example.gronurgrocery.common.main_app.navigation.screens.Login
import com.example.gronurgrocery.common.main_app.navigation.screens.Onboarding
import com.example.gronurgrocery.common.main_app.navigation.screens.Register
import com.example.gronurgrocery.common.main_app.navigation.screens.ResetPassword
import com.example.gronurgrocery.common.main_app.navigation.screens.SetUpAccount
import com.example.gronurgrocery.common.main_app.navigation.screens.Splash
import com.example.gronurgrocery.common.main_app.navigation.screens.Verification
import com.example.gronurgrocery.features.auth.domain.model.RegisterData
import com.example.gronurgrocery.features.auth.presentation.forgot_password.ForgotPasswordScreen
import com.example.gronurgrocery.features.auth.presentation.login.LoginScreen
import com.example.gronurgrocery.features.auth.presentation.register.RegisterScreen
import com.example.gronurgrocery.features.auth.presentation.reset_password.ResetPasswordScreen
import com.example.gronurgrocery.features.auth.presentation.set_up_account.SetUpAccountScreen
import com.example.gronurgrocery.features.auth.presentation.verification.VerificationScreen
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
        startDestination = Splash
    ) {
        composable<Splash> {
            val coroutineScope = rememberCoroutineScope()
            LaunchedEffect(key1 = true) {
                coroutineScope.launch {
                    delay(SPLASH_DELAY_TIME)
                    if (viewModel.onboardingState.value) {
                        navController.navigate(route = Register) {
                            popUpTo(route = Splash) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(route = Onboarding) {
                            popUpTo(route = Splash) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            SplashScreen()
        }

        composable<Onboarding> {
            OnboardingPager(
                onBackPressed = { navController.popBackStack() },
                onLastContinuePressed = {
                    navController.navigate(route = Register) {
                        popUpTo(route = Onboarding) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<Register> {
            RegisterScreen(
                onSignInClick = {
                    navController.navigate(route = Login())
                },
                onSignUpClick = { regData ->

                    navController.navigate(
                        route = SetUpAccount(
                            emailText = regData.emailText,
                            password = regData.passwordText
                        )
                    )
                },
                onUpButtonPressed = { activity.finish() }
            )
        }

        composable<Login> {
            val args = it.toRoute<Login>()
            LoginScreen(
                email = args.emailText,
                password = args.passwordText,
                onSignUpClick = {
                    navController.navigate(route = Register) {
                        popUpTo(route = Register) {
                            inclusive = true
                        }
                    }
                },
                onUpButtonPressed = { navController.navigateUp() },
                onForgotPasswordClick = { navController.navigate(ForgotPassword) }
            )
        }

        composable<ForgotPassword> {
            ForgotPasswordScreen(
                navigateToVerification = { navController.navigate(Verification(emailText = it)) },
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<Verification> {
            val args = it.toRoute<Verification>()
            VerificationScreen(
                email = args.emailText,
                navigateToReset = { navController.navigate(ResetPassword(args.emailText)) },
                onUpButtonPressed = { navController.navigateUp() }
            )
        }

        composable<ResetPassword> {
            val args = it.toRoute<ResetPassword>()

            ResetPasswordScreen(
                email = args.emailText,
                onSaveClick = { emailText, passwordText ->
                    navController.navigate(
                        route = Login(
                            emailText = emailText,
                            passwordText = passwordText
                        )
                    ) {
                        popUpTo(route = Login()) {
                            inclusive = true
                        }
                    }
                },
                onUpButtonPressed = { navController.navigateUp() }
            )
        }
        composable<SetUpAccount> {
            val args = it.toRoute<SetUpAccount>()
            val registerData = RegisterData(
                emailText = args.emailText,
                passwordText = args.password,
                confirmPasswordText = args.password
            )
            SetUpAccountScreen(
                onSaveChangesClick = { /* TODO */ },
                onUpButtonPressed = { navController.navigateUp() },
                registerData = registerData
            )
        }
    }
}