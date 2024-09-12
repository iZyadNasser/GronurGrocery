package com.example.gronurgrocery.features.auth.presentation.set_up_account

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.auth.domain.model.RegisterData
import com.example.gronurgrocery.features.auth.presentation.common.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.common.components.FormText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextFieldErrorText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun SetUpAccountScreen(
    onSaveChangesClick: () -> Unit,
    onUpButtonPressed: () -> Unit,
    registerData: RegisterData,
    modifier: Modifier = Modifier,
    setUpAccountViewModel: SetUpAccountViewModel = viewModel<SetUpAccountViewModel>()
) {

    val uiState = setUpAccountViewModel.state.value
    setUpAccountViewModel.initializeForm(registerData)

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
                end = 24.dp
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
                    titleText = "Set Up Your Account",
                    descriptionText = "Complete your account setup by providing  your proper biography info.",
                    modifier = Modifier
                        .padding(
                            top = 12.dp
                        )
                )

                Spacer(modifier = Modifier.height(40.dp))
                FormTextField(
                    label = "Enter Your Full Name",
                    iconDrawable = R.drawable.profile,
                    fieldValue = uiState.fullNameText,
                    onValueChange = { setUpAccountViewModel.updateFullNameState(it) },
                    isError = uiState.fullNameError != null,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (uiState.fullNameError != null) {
                    if (uiState.fullNameText.isBlank()) {
                        FormTextFieldErrorText(
                            text = "This field is required",
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    } else {
                        FormTextFieldErrorText(
                            text = uiState.fullNameError,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                FormTextField(
                    label = "Enter Your Email Address",
                    disabled = true,
                    iconDrawable = R.drawable.sms,
                    fieldValue = uiState.emailText,
                    onValueChange = { setUpAccountViewModel.updateEmailState(it) },
                    isError = uiState.emailError != null,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
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

                Spacer(modifier = Modifier.height(20.dp))
                FormTextField(
                    label = "Enter Your Phone Number",
                    iconDrawable = R.drawable.call_calling,
                    fieldValue = uiState.phoneNumberText,
                    onValueChange = { setUpAccountViewModel.updatePhoneNumberState(it) },
                    isError = uiState.phoneNumberError != null,
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (uiState.phoneNumberError != null) {
                    if (uiState.phoneNumberText.isBlank()) {
                        FormTextFieldErrorText(
                            text = "This field is required",
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    } else {
                        FormTextFieldErrorText(
                            text = uiState.phoneNumberError,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                FormTextField(
                    label = "Password",
                    iconDrawable = R.drawable.lock,
                    fieldValue = uiState.passwordText,
                    visibilityIconDrawable = if (uiState.isPasswordVisible) R.drawable.outline_visibility else R.drawable.outline_visibility_off,
                    onVisibilityIconClick = { setUpAccountViewModel.togglePasswordVisibility() },
                    onValueChange = { setUpAccountViewModel.updatePasswordState(it) },
                    isError = uiState.passwordError != null,
                    visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (uiState.passwordError != null) {
                    if (uiState.passwordText.isBlank()) {
                        FormTextFieldErrorText(
                            text = "This field is required",
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    } else {
                        FormTextFieldErrorText(
                            text = uiState.passwordError,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                FormTextField(
                    label = "Confirm Password",
                    iconDrawable = R.drawable.lock,
                    visibilityIconDrawable = if (uiState.isConfirmPasswordVisible) R.drawable.outline_visibility else R.drawable.outline_visibility_off,
                    onVisibilityIconClick = { setUpAccountViewModel.toggleConfirmPasswordVisibility() },
                    fieldValue = uiState.confirmPasswordText,
                    onValueChange = { setUpAccountViewModel.updateConfirmPasswordState(it) },
                    isError = uiState.confirmPasswordError != null,
                    visualTransformation = if (uiState.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (uiState.confirmPasswordError != null) {
                    if (uiState.confirmPasswordText.isBlank()) {
                        FormTextFieldErrorText(
                            text = "This field is required",
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    } else {
                        FormTextFieldErrorText(
                            text = uiState.confirmPasswordError,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }
            }

            Column {
                FormButton(
                    text = "Save Changes",
                    onClick = {
                        onSaveChangesClick()
                        // TODO (add more logic)
                    },
                )
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewSetUpAccountScreen() {
    GronurGroceryTheme {
        SetUpAccountScreen(
            onUpButtonPressed = {},
            onSaveChangesClick = {},
            registerData = RegisterData(
                emailText = "zyadnasser@zezo.zed",
                passwordText = "11111zZ#",
                confirmPasswordText = "11111zZ#"
            )
        )
    }
}