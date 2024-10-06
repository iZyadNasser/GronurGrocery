package com.example.gronurgrocery.features.profile.presentation.password

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.common.presentation.ui.components.DarkPageContainerWithBackButton
import com.example.gronurgrocery.common.presentation.ui.components.FormTextField
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun Password(
    onUpButtonButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    DarkPageContainerWithBackButton(
        onBackButtonPressed = { onUpButtonButtonPressed() },
        title = "Password",
        preContent = { },
        content = { PasswordScreen() }
    )
}

@Composable
private fun PasswordScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .fillMaxSize()
            .background(Color(0xFFF4F5F7))
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}

@Composable
private fun PasswordInfoField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    label: String,
    @DrawableRes iconRes: Int
) {

    Text(
        text = text,
        color = background,
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    )

    Spacer(modifier = Modifier.height(16.dp))
    FormTextField(
        label = label,
        onVisibilityIconClick = {/* TODO */},
        iconDrawable = iconRes,
        fieldValue = value,
        onValueChange = { onValueChange(it) },
        iconTint = background,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun PreviewPassword() {
    GronurGroceryTheme {
        Password(onUpButtonButtonPressed = { })
    }
}