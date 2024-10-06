package com.example.gronurgrocery.common.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun FormTextField(
    onClick: (() -> Unit)? = null,
    label: String,
    readOnly: Boolean = false,
    @DrawableRes iconDrawable: Int? = null,
    fieldValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    disabled: Boolean = false,
    @DrawableRes visibilityIconDrawable: Int? = null,
    onVisibilityIconClick: (() -> Unit)? = null,
    onFocusChanged: (FocusState) -> Unit = {/* TODO: Remove this default lambda */},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType? = null,
    imeAction: ImeAction? = null,
    onActionButtonClick: (() -> Unit)? = null,
    iconTint: Color = Color(0xFF96A4B2),
    placeholderColor: Color = Color(0xFF96A4B2),
    isPassword: Boolean = true
) {
        TextField(
            readOnly = readOnly,
            value = fieldValue,
            enabled = !disabled,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            isError = isError,
            leadingIcon =
                if (iconDrawable != null) {
                    {
                        Icon(
                            painter = painterResource(id = iconDrawable),
                            contentDescription = "email",
                            tint = iconTint
                        )
                    }
                } else {
                    null
                },
            trailingIcon = {
                if (visibilityIconDrawable != null) {
                    if (isPassword) {
                        Icon(
                            painter = painterResource(id = visibilityIconDrawable),
                            contentDescription = "toggle password visibility",
                            tint = iconTint,
                            modifier = if (onVisibilityIconClick != null) {
                                Modifier
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                    ) {
                                        onVisibilityIconClick()
                                    }
                                    .animateContentSize()
                            } else {
                                Modifier
                                    .animateContentSize()
                            }
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = visibilityIconDrawable),
                            contentDescription = "expand",
                            tint = iconTint,
                            modifier = if (onVisibilityIconClick != null) {
                                Modifier
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                    ) {
                                        onVisibilityIconClick()
                                    }
                                    .animateContentSize()
                            } else {
                                Modifier
                                    .animateContentSize()
                            }
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = placeholderColor
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledContainerColor = Color(0xFFCECACA),
                cursorColor = background
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                letterSpacing = 0.sp,
                //fontFamily = FontFamily(Font(R.font.inter_bold)),
                textAlign = TextAlign.Start
            ),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType ?: KeyboardType.Text,
                imeAction = imeAction ?: ImeAction.Default
            ),
            keyboardActions = if (onActionButtonClick != null) { KeyboardActions(
                onDone = {
                    onActionButtonClick()
                }
            )} else {
                KeyboardActions.Default
            }
            ,
            modifier = modifier
                .clip(RoundedCornerShape(30.dp))
                .height(60.dp)
                .background(if (disabled) Color(0xFFCECACA) else if (isError) TextFieldDefaults.colors().errorContainerColor else Color(0xFFF8F8F8))
                .onFocusChanged { onFocusChanged(it) }
                .padding(
                    top = 4.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 4.dp
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    if (onClick != null) {
                        onClick()
                    }
                }
        )
}

@Preview
@Composable
private fun PreviewFormTextField() {
    GronurGroceryTheme {
        FormTextField(
            label = "Email Address",
            iconDrawable = R.drawable.sms,
            visibilityIconDrawable = R.drawable.outline_visibility_off,
            fieldValue = "",
            onValueChange = {},
            onFocusChanged = {}
        )
    }
}