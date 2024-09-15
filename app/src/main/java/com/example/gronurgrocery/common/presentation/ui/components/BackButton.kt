package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color(0x66FFFFFF),
    arrowColor: Color = Color.White
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .width(40.dp)
            .height(56.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(100.dp)
            )
            .background(Color.Transparent)
            .clickable {
                onClick()
            }
    ) {
        Icon(painter = painterResource(id = R.drawable.arrow_left), contentDescription = "search", tint = arrowColor)
    }
}

@Preview
@Composable
private fun PreviewBackButton() {
    GronurGroceryTheme {
        BackButton({})
    }
}