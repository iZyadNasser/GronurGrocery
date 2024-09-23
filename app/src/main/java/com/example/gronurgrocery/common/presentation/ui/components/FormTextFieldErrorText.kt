package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun FormTextFieldErrorText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "*$text",
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic
        ),
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
    )
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewFormTextFieldErrorText() {
    GronurGroceryTheme {
        FormTextFieldErrorText(
            text = "Please add stuff to the thing"
        )
    }
}