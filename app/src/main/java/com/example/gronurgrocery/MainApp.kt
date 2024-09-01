package com.example.gronurgrocery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gronurgrocery.features.starting.screens.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable(route = "splash") {
            val scope = rememberCoroutineScope()
            LaunchedEffect(key1 = true) {
                scope.launch {
                    delay(1500)
                    navController.navigate(route = "onboarding") {
                        popUpTo(route = "splash") {
                            inclusive = true
                        }
                    }
                }
            }
            SplashScreen()
        }

        composable(route = "onboarding") {

        }
    }
}