package com.example.gronurgrocery.features.auth.presentation.reset_password

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
import com.example.gronurgrocery.features.auth.presentation.common.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.common.components.FormText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextFieldErrorText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun ResetPasswordScreen(
    onSaveClick: () -> Unit,
    onUpButtonPressed: () -> Unit,
    resetPasswordViewModel: ResetPasswordViewModel = viewModel<ResetPasswordViewModel>()
) {

    val uiState = resetPasswordViewModel.state.value

    Column(
        modifier = Modifier
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
                    titleText = "Reset Password",
                    descriptionText = "Ensure the security of your account by selecting a robust and fortified password.",
                    modifier = Modifier
                        .padding(
                            top = 12.dp
                        )
                )


                Spacer(modifier = Modifier.height(20.dp))
                FormTextField(
                    label = "New Password",
                    iconDrawable = R.drawable.lock,
                    fieldValue = uiState.passwordText,
                    visibilityIconDrawable = if (uiState.isPasswordVisible) R.drawable.outline_visibility else R.drawable.outline_visibility_off,
                    onVisibilityIconClick = { resetPasswordViewModel.togglePasswordVisibility() },
                    onValueChange = { resetPasswordViewModel.updatePasswordState(it) },
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
                    onVisibilityIconClick = { resetPasswordViewModel.toggleConfirmPasswordVisibility() },
                    fieldValue = uiState.confirmPasswordText,
                    onValueChange = { resetPasswordViewModel.updateConfirmPasswordState(it) },
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
                    text = "Save",
                    onClick = {
                        onSaveClick()
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
private fun PreviewResetPasswordScreen() {
    GronurGroceryTheme {
        ResetPasswordScreen(
            onSaveClick = {},
            onUpButtonPressed = {}
        )
    }
}