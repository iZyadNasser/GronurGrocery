package com.example.gronurgrocery.features.profile.presentation.account_info

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.DarkPageContainerWithBackButton
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.common.presentation.ui.components.FormTextField
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun AccountInfo(
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    DarkPageContainerWithBackButton(
        onBackButtonPressed = { onUpButtonPressed() },
        title = "Account Information",
        preContent = { },
        content = {
            AccountInfoScreen()
        }
    )
}

@Composable
private fun AccountInfoScreen(
    modifier: Modifier = Modifier,
    accountInfoViewModel: AccountInfoViewModel = viewModel<AccountInfoViewModel>()
) {

    val uiState = accountInfoViewModel.state.value

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
            modifier = Modifier
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            InfoField(
                text = "Name",
                label = "Enter Your Full Name",
                iconRes = R.drawable.frame,
                value = uiState.nameText,
                onValueChange = { accountInfoViewModel.updateNameState(it) }
            )
            Spacer(modifier = Modifier.height(28.dp))

            InfoField(
                text = "Username",
                label = "Enter Your Username",
                iconRes = R.drawable.frame,
                value = uiState.usernameText,
                onValueChange = { accountInfoViewModel.updateUsernameState(it) }
            )
            Spacer(modifier = Modifier.height(28.dp))

            InfoField(
                text = "Email address",
                label = "Enter Your Email Address",
                iconRes = R.drawable.direct,
                value = uiState.emailText,
                onValueChange = {},
                disabled = true
            )
            Spacer(modifier = Modifier.height(28.dp))

            InfoField(
                text = "Phone number",
                label = "Enter Your Phone Number",
                iconRes = R.drawable.phone_profile,
                value = uiState.phoneNumText,
                onValueChange = { accountInfoViewModel.updatePhoneNumState(it) }
            )

            Spacer(modifier = Modifier.height(68.dp))
            FormButton(
                text = "Save",
                onClick = {
                    /* TODO( save data ) */
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun InfoField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    label: String,
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    disabled: Boolean = false
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
        iconDrawable = iconRes,
        fieldValue = value,
        onValueChange = { onValueChange(it) },
        iconTint = background,
        disabled = disabled,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun PreviewAccountInfo() {
    GronurGroceryTheme {
        AccountInfo(
            onUpButtonPressed = {}
        )
    }
}