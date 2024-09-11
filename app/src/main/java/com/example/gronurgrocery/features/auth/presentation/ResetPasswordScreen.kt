package com.example.gronurgrocery.features.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun ResetPasswordScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Hello From ResetPassword")
    }
}

@Preview
@Composable
private fun PreviewResetPasswordScreen() {
    GronurGroceryTheme {
        ResetPasswordScreen(

        )
    }
}