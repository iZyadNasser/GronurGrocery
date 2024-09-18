package com.example.gronurgrocery.features.auth.presentation.forgot_password

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.common.components.FormText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextFieldErrorText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun ForgotPasswordScreen(
    navigateToVerification: (String) -> Unit,
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    forgotPasswordViewModel: ForgotPasswordViewModel = viewModel<ForgotPasswordViewModel>()
) {

    val uiState = forgotPasswordViewModel.state.value

    Column(
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
            )
    ) {
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
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        bottom = 36.dp
                    )
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
                    fieldValue = uiState.emailText,
                    onValueChange = { forgotPasswordViewModel.updateEmailState(it) },
                    isError = uiState.emailError != null,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (uiState.emailError != null) {
                    if (uiState.emailText.isBlank()) {
                        FormTextFieldErrorText(
                            text = "This field is required",
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    } else {
                        FormTextFieldErrorText(
                            text = uiState.emailError,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }
            }
            Column {
                FormButton(
                    text = "Next",
                    onClick = {
                        if (forgotPasswordViewModel.allDataValid()) {
                            navigateToVerification(uiState.emailText)
                        }
                        // TODO (add more logic)
                    },
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
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