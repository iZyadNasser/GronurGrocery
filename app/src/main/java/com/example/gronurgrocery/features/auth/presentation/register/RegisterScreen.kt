package com.example.gronurgrocery.features.auth.presentation.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.AppTitle
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus
import com.example.gronurgrocery.features.auth.presentation.common.components.FormContinueWithButton
import com.example.gronurgrocery.features.auth.presentation.common.components.FormDivider
import com.example.gronurgrocery.features.auth.presentation.common.components.FormText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.common.components.FormTextFieldErrorText
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun RegisterScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: (RegData) -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val uiState = registerViewModel.state.value

    if (uiState.responseStatus == ResponseStatus.SUCCESS) {
        registerViewModel.endRegisterProcess()
        val regData = RegData(
            emailText = uiState.emailText,
            passwordText = uiState.passwordText,
            confirmPasswordText = uiState.confirmPasswordText,
            tokenText = uiState.token
        )
        onSignUpClick(regData)
    } else if (uiState.responseStatus == ResponseStatus.FAILURE) {
        registerViewModel.endRegisterProcess()
        val context = LocalContext.current
        Toast.makeText(
            context,
            uiState.registerErrorMsg ?: "An unexpected error happened",
            Toast.LENGTH_SHORT
        ).show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                AppTitle(title = "Daily Grocery Food")
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                FormText(
                    titleText = "Register Now",
                    descriptionText = "Sign up with your email and password to continue",
                    modifier = Modifier
                        .padding(
                            top = 12.dp
                        )
                )

                Spacer(modifier = Modifier.height(40.dp))
                FormTextField(
                    label = "Email Address",
                    iconDrawable = R.drawable.sms,
                    fieldValue = uiState.emailText,
                    onValueChange = { registerViewModel.updateEmailState(it) },
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
                    label = "Password",
                    iconDrawable = R.drawable.lock,
                    fieldValue = uiState.passwordText,
                    visibilityIconDrawable = if (uiState.isPasswordVisible) R.drawable.outline_visibility else R.drawable.outline_visibility_off,
                    onVisibilityIconClick = { registerViewModel.togglePasswordVisibility() },
                    onValueChange = { registerViewModel.updatePasswordState(it) },
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
                    onVisibilityIconClick = { registerViewModel.toggleConfirmPasswordVisibility() },
                    fieldValue = uiState.confirmPasswordText,
                    onValueChange = { registerViewModel.updateConfirmPasswordState(it) },
                    isError = (uiState.confirmPasswordError != null || (uiState.confirmPasswordText.isBlank() && uiState.anyError)),
                    visualTransformation = if (uiState.isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (uiState.confirmPasswordError != null || (uiState.confirmPasswordText.isBlank() && uiState.anyError)) {
                    if (uiState.confirmPasswordError != null) {
                        FormTextFieldErrorText(
                            text = uiState.confirmPasswordError,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    } else {
                        FormTextFieldErrorText(
                            text = "This field is required",
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
                FormButton(
                    text = "Sign Up",
                    onClick = {
                        if (registerViewModel.allDataValid()) {
                            registerViewModel.sendRegisterData()
                            Log.e("TAG", uiState.responseStatus.toString())
                        } else {
                            /* TODO */
                        }
                        /* TODO */
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
                FormDivider()
                Spacer(modifier = Modifier.height(32.dp))

                FormContinueWithButton(
                    text = "Continue with Google",
                    iconRes = R.drawable.google,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                FormContinueWithButton(
                    text = "Continue with Apple",
                    iconRes = R.drawable.apple,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(52.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF96A4B2)
                    )
                    Text(
                        text = " Sign In.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF19253D),
                        modifier = Modifier
                            .clickable { onSignInClick() }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }

        if (uiState.responseStatus == ResponseStatus.LOADING) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .alpha(0.2f)
                    .clickable {  }
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewRegisterScreen() {
    GronurGroceryTheme {
        RegisterScreen(
            onSignInClick = {},
            onSignUpClick = {},
            //onUpButtonPressed = {}
        )
    }
}