package com.example.gronurgrocery.features.auth.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background


@Composable
fun CustomCheckbox(
    checked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeColor = if (!checked) Color(0xFFD4DEE7) else Color(0xFF19253D)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .border(BorderStroke(1.dp, activeColor), shape = RoundedCornerShape(5.dp))
            .size(20.dp)
            .background(if (!checked) Color.Transparent else background)
            .clickable { onClick() }
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Checked",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCustomCheckboxChecked() {
    GronurGroceryTheme {
        CustomCheckbox(
            checked = true,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewCustomCheckboxUnchecked() {
    GronurGroceryTheme {
        CustomCheckbox(
            checked = false,
            onClick = {}
        )
    }
}