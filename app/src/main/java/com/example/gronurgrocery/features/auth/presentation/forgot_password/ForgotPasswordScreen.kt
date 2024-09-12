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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.auth.presentation.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.components.FormText
import com.example.gronurgrocery.features.auth.presentation.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.components.FormTextFieldErrorText
import com.example.gronurgrocery.features.auth.presentation.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun ForgotPasswordScreen(
    navigateToVerification: () -> Unit,
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    forgotPasswordViewModel: ForgotPasswordViewModel = viewModel<ForgotPasswordViewModel>()
) {

    val uiState = forgotPasswordViewModel.state.value

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