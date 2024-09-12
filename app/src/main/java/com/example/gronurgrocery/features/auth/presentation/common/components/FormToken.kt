package com.example.gronurgrocery.features.auth.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun FormToken(
    modifier: Modifier = Modifier,
    length: Int = 0,
    token: String = "",
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
    ) {
        for (i in 0..<length) {
            FormTokenDigit(
                text = (if (i < token.length && token[i].isDigit()) token[i].toString() else null)
            )
            if (i != length - 1) {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }

}

@Composable
private fun FormTokenDigit(
    modifier: Modifier = Modifier,
    text: String? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .size(60.dp)
            .background(Color(0xFFF8F8F8))
    ) {
        if (text != null) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = background
                )
            )
        } else {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .size(6.dp)
                    .background(Color(0xFF96A4B2))
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFormTokenDigitEmpty() {
    GronurGroceryTheme {
        FormTokenDigit(

        )
    }
}

@Preview
@Composable
private fun PreviewFormTokenDigitFilled() {
    GronurGroceryTheme {
        FormTokenDigit(
            text = "4"
        )
    }
}

@Preview
@Composable
private fun PreviewFormToken() {
    GronurGroceryTheme {
        FormToken(
            length = 4,
            onClick = {}
        )
    }
}