package com.example.gronurgrocery.features.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.auth.presentation.components.CustomCheckbox
import com.example.gronurgrocery.features.auth.presentation.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.components.FormContinueWithButton
import com.example.gronurgrocery.features.auth.presentation.components.FormDivider
import com.example.gronurgrocery.features.auth.presentation.components.FormText
import com.example.gronurgrocery.features.auth.presentation.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onUpButtonPressed: () -> Unit,
    onForgotPasswordClick: () -> Unit,
) {
    var emailTextFieldState by remember {
        mutableStateOf("")
    }

    var passwordTextFieldState by remember {
        mutableStateOf("")
    }

    var savePasswordChecked by remember { mutableStateOf(false) }

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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            FormText(
                titleText = "Welcome back",
                descriptionText = "Access your account securely by using your email and password",
                modifier = Modifier
                    .padding(
                        top = 12.dp
                    )
            )

            Spacer(modifier = Modifier.height(40.dp))
            FormTextField(
                label = "Email Address",
                iconDrawable = R.drawable.sms,
                fieldValue = emailTextFieldState,
                onValueChange = { emailTextFieldState = it },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))
            FormTextField(
                label = "Password",
                iconDrawable = R.drawable.lock,
                fieldValue = passwordTextFieldState,
                onValueChange = { passwordTextFieldState = it },
                modifier = Modifier
                    .fillMaxWidth()
            )

            // TODO save password
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomCheckbox(
                        checked = savePasswordChecked,
                        onClick = { savePasswordChecked = !savePasswordChecked },
                        modifier = Modifier
                            .size(20.dp)
                    )

                    Text(
                        text = "Save password",
                        style = TextStyle(
                            textAlign = TextAlign.End,
                            letterSpacing = 0.sp,
                            lineHeight = 26.sp,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        ),
                        color = Color(0xFF96A4B2),
                        modifier = Modifier
                            .padding(
                                start = 12.dp
                            )
                    )
                }

                Text(
                    text = "Forgot password?",
                    color = Color(0xFFFF5C01),
                    style = TextStyle(
                        textAlign = TextAlign.End,
                        letterSpacing = 0.sp,
                        lineHeight = 26.sp,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .clickable { onForgotPasswordClick() }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            FormButton(
                text = "Sign In",
                onClick = { /*TODO*/ }
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
                    text = "Didn’t have an account?",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF96A4B2)
                )
                Text(
                    text = " Sign Up.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF19253D),
                    modifier = Modifier
                        .clickable { onSignUpClick() }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewLoginScreen() {
    GronurGroceryTheme {
        LoginScreen(
            onSignUpClick = {},
            onUpButtonPressed = {},
            onForgotPasswordClick = {}
        )
    }
}