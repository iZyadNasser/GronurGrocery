package com.example.gronurgrocery.features.auth.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
    label: String,
    @DrawableRes iconDrawable: Int,
    fieldValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes visibilityIconDrawable: Int? = null,
    onVisibilityIconClick: (() -> Unit)? = null,
    onFocusChanged: (FocusState) -> Unit = {/* TODO: Remove this default lambda */},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType? = null,
    imeAction: ImeAction? = null,
    onActionButtonClick: (() -> Unit)? = null
) {
        TextField(
            value = fieldValue,
            onValueChange = { onValueChange(it) },
            singleLine = true,
            isError = isError,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = iconDrawable),
                    contentDescription = "email",
                    tint = Color(0xFF96A4B2)
                )
            },
            trailingIcon = {
                if (visibilityIconDrawable != null) {
                    Icon(
                        painter = painterResource(id = visibilityIconDrawable),
                        contentDescription = "toggle password visibility",
                        tint = Color(0xFF96A4B2),
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
            },
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF96A4B2)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
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

            modifier = modifier
                .clip(RoundedCornerShape(30.dp))
                .height(60.dp)
                .background(Color(0xFFF8F8F8))
                .onFocusChanged { onFocusChanged(it) }
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