package com.example.gronurgrocery.features.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.auth.presentation.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.components.FormText
import com.example.gronurgrocery.features.auth.presentation.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun ForgotPasswordScreen(
    navigateToVerification: () -> Unit,
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var emailTextFieldState by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(
                top = 16.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = 36.dp
            )
    ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(
                        bottom = 12.dp
                    )
            ) {
                FormUpButton(
                    onClick = { onUpButtonPressed() }
                )
            }

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                FormText(
                    titleText = "Forgot Password",
                    descriptionText = "Provide the email address associated with your account in the designated field.",
                    modifier = Modifier
                        .padding(
                            top = 12.dp
                        )
                )

                Spacer(modifier = Modifier.height(40.dp))
                FormTextField(
                    label = "Enter Email Address",
                    iconDrawable = R.drawable.sms,
                    fieldValue = emailTextFieldState,
                    onValueChange = { emailTextFieldState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        FormButton(
            text = "Next",
            onClick = {
                navigateToVerification()
                // TODO (add more logic)
            },
        )
    }
}

@Preview
@Composable
private fun PreviewForgotPasswordScreen() {
    GronurGroceryTheme {
        ForgotPasswordScreen(
            navigateToVerification = {},
            onUpButtonPressed = { /*TODO*/ }
        )
    }
}