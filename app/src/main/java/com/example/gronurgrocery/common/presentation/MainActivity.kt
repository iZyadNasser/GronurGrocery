package com.example.gronurgrocery.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.gronurgrocery.common.presentation.navigation.MyApp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GronurGroceryTheme {
                MyApp(
                    navController = rememberNavController()
                )
            }
        }
    }
}