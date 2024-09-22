package com.example.gronurgrocery.features.auth.presentation.verification

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus
import com.example.gronurgrocery.features.auth.presentation.common.components.FormText
import com.example.gronurgrocery.features.auth.presentation.common.components.FormToken
import com.example.gronurgrocery.features.auth.presentation.common.components.FormUpButton
import com.example.gronurgrocery.features.auth.presentation.common.hideMail
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun VerificationScreen(
    email: String,
    navigateToReset: () -> Unit,
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    verificationViewModel: VerificationViewModel = viewModel<VerificationViewModel>()
) {

    val uiState = verificationViewModel.state.value

    val tokenFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    if (uiState.verifyStatus == ResponseStatus.SUCCESS) {
        verificationViewModel.endResponse()
        navigateToReset()
    } else if (uiState.verifyStatus == ResponseStatus.FAILURE) {
        verificationViewModel.endResponse()
        Toast.makeText(context, uiState.errorMsg ?: "", Toast.LENGTH_SHORT).show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) { focusManager.clearFocus() }

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
                        titleText = "Verification Code",
                        descriptionText = "We’ve sent the code to your mail address that you include: ${
                            hideMail(
                                email
                            )
                        }",
                        modifier = Modifier
                            .padding(
                                top = 12.dp
                            )
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextField(
                            value = uiState.tokenText,
                            onValueChange = {
                                var itt = ""
                                for (c in it) {
                                    if (c.isDigit()) {
                                        itt += c
                                    }
                                }
                                if (itt.length <= 4) {
                                    verificationViewModel.updateTokenText(itt)
                                }
                            },
                            colors = TextFieldColors(
                                focusedContainerColor = Color.Transparent,
                                focusedTextColor = Color.Transparent,
                                unfocusedTextColor = Color.Transparent,
                                disabledTextColor = Color.Transparent,
                                errorTextColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                errorContainerColor = Color.Transparent,
                                cursorColor = Color.Transparent,
                                errorCursorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                errorIndicatorColor = Color.Transparent,
                                focusedLeadingIconColor = Color.Transparent,
                                unfocusedLeadingIconColor = Color.Transparent,
                                disabledLeadingIconColor = Color.Transparent,
                                errorLeadingIconColor = Color.Transparent,
                                focusedTrailingIconColor = Color.Transparent,
                                unfocusedTrailingIconColor = Color.Transparent,
                                disabledTrailingIconColor = Color.Transparent,
                                errorTrailingIconColor = Color.Transparent,
                                focusedLabelColor = Color.Transparent,
                                unfocusedLabelColor = Color.Transparent,
                                disabledLabelColor = Color.Transparent,
                                errorLabelColor = Color.Transparent,
                                focusedPlaceholderColor = Color.Transparent,
                                unfocusedPlaceholderColor = Color.Transparent,
                                disabledPlaceholderColor = Color.Transparent,
                                errorPlaceholderColor = Color.Transparent,
                                focusedSupportingTextColor = Color.Transparent,
                                unfocusedSupportingTextColor = Color.Transparent,
                                disabledSupportingTextColor = Color.Transparent,
                                errorSupportingTextColor = Color.Transparent,
                                focusedPrefixColor = Color.Transparent,
                                unfocusedPrefixColor = Color.Transparent,
                                disabledPrefixColor = Color.Transparent,
                                errorPrefixColor = Color.Transparent,
                                focusedSuffixColor = Color.Transparent,
                                unfocusedSuffixColor = Color.Transparent,
                                disabledSuffixColor = Color.Transparent,
                                errorSuffixColor = Color.Transparent,
                                textSelectionColors = TextSelectionColors(
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .focusRequester(tokenFocusRequester)
                        )
                        FormToken(
                            length = 4,
                            token = uiState.tokenText,
                            onClick = {
                                focusManager.clearFocus()
                                tokenFocusRequester.requestFocus()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Resend Code",
                        color = uiState.resendButtonColor,
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },
                            ) {
                                verificationViewModel.onResendButtonClicked()
                            }
                    )
                }
                Column {
                    FormButton(
                        text = "Next",
                        onClick = {
                            if (verificationViewModel.isValid()) {
                                verificationViewModel.sendVerification()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }
        }

        if (uiState.verifyStatus == ResponseStatus.LOADING) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .alpha(0.2f)
                    .clickable { }
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewVerificationScreen() {
    GronurGroceryTheme {
        VerificationScreen(
            email = "zyadhammad531@gmail.com",
            navigateToReset = {},
            onUpButtonPressed = {}
        )
    }
}