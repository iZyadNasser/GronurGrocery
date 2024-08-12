package com.example.gronurgrocery.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gronurgrocery.ui.screens.logo.LogoScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController
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
            LogoScreen()
        }

        composable(route = "onboarding") {

        }
    }
}