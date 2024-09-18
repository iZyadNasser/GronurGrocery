package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun FormButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        textColor = Color.White,
        backgroundColor = background,
        onClick = { onClick() },
        modifier = modifier
    )
}

@Preview
@Composable
private fun PreviewFormButton() {
    GronurGroceryTheme {
        FormButton(
            text ="Sign In",
            onClick = {}
        )
    }
}