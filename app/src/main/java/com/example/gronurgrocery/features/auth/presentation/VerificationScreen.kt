package com.example.gronurgrocery.features.auth.presentation

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
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.features.auth.presentation.components.FormButton
import com.example.gronurgrocery.features.auth.presentation.components.FormText
import com.example.gronurgrocery.features.auth.presentation.components.FormToken
import com.example.gronurgrocery.features.auth.presentation.components.FormUpButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme

@Composable
fun VerificationScreen(
    navigateToReset: () -> Unit,
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    var tokenState by remember {
        mutableStateOf("")
    }

    val tokenFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

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
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) { focusManager.clearFocus() }

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
                    titleText = "Verification Code",
                    descriptionText = "We’ve sent the code to your mail address that you include: sha.....@gmail.com", // TODO (replace placeholder email with actual email written)
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
                        value = tokenState,
                        onValueChange = {
                            var itt = ""
                            for (c in it) {
                                if (c.isDigit()) {
                                    itt += c
                                }
                            }
                            if (itt.length <= 4) {
                                tokenState = itt
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
                            textSelectionColors = TextSelectionColors(Color.Transparent, Color.Transparent)
                        ),
                        modifier = Modifier
                            .focusRequester(tokenFocusRequester)
                    )
                    FormToken(
                        length = 4,
                        token = tokenState,
                        onClick = {
                            focusManager.clearFocus()
                            tokenFocusRequester.requestFocus()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Resend Code",
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
                        ) { /* TODO */ }
                )
            }
        }
        FormButton(
            text = "Next",
            onClick = {
                navigateToReset()
                // TODO (add more logic)
            }
        )
    }
}

@Preview
@Composable
private fun PreviewVerificationScreen() {
    GronurGroceryTheme {
        VerificationScreen(
            navigateToReset = {},
            onUpButtonPressed = {}
        )
    }
}