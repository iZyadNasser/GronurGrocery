package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun AppTitle(
    title: String,
    modifier: Modifier = Modifier,
    height: Int? = null
) {
    Text(
        textAlign = TextAlign.Start,
        text = title,
        color = background,
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = if (height != null) modifier
            .height(height.dp)
            .padding(
                top = 10.dp
            )
        else modifier
            .padding(
                top = 10.dp
            )
    )
}

@Preview
@Composable
private fun PreviewAppTitle() {
    GronurGroceryTheme {
        AppTitle("Gronur Grocery")
    }
}