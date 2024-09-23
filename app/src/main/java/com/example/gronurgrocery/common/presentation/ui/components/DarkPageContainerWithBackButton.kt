package com.example.gronurgrocery.common.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun DarkPageContainerWithBackButton(
    onBackButtonPressed: () -> Unit,
    title: String,
    preContent: (@Composable () -> Unit)?,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    putMask: (() -> Unit)? = null,
    onContainerClick: (() -> Unit)? = null,
    qrIconAction: (() -> Unit)? = null,
) {
    if (putMask != null && onContainerClick != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(background)
                .paint(
                    painter = painterResource(id = R.drawable.inner_pattern),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopStart,
                    alpha = 0.6f
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    onContainerClick()
                }
        ) {
            Column(
                modifier = Modifier
                    .alpha(0.6f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 36.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        BackButton(
                            onClick = onBackButtonPressed
                        )
                    }

                    Text(
                        text = title,
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.sp
                        ),
                        modifier = Modifier
                            .weight(3f)
                    )
                    if (qrIconAction != null) {
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier.weight(1f)
                        ) {
                            QRButton(onClick = { /*TODO*/ })
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))
                if (preContent != null) {
                    preContent()
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            content()
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(background)
                .paint(
                    painter = painterResource(id = R.drawable.inner_pattern),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.TopStart
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 36.dp
                    )
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    BackButton(
                        onClick = { onBackButtonPressed() }
                    )
                }

                Text(
                    text = title,
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.sp
                    ),
                    modifier = Modifier
                        .weight(3f)
                )
                if (qrIconAction != null) {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier.weight(1f)
                    ) {
                        QRButton(onClick = { /*TODO*/ })
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            if (preContent != null) {
                preContent()
                Spacer(modifier = Modifier.height(8.dp))
            }

            content()
        }
    }
}

@Composable
private fun QRButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color(0x66FFFFFF),
    arrowColor: Color = Color.White,
    backgroundColor: Color = Color.Transparent
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .width(40.dp)
            .height(56.dp)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(100.dp)
            )
            .background(backgroundColor)
            .clickable {
                onClick()
            }
    ) {
        Icon(painter = painterResource(id = R.drawable.qr_code), contentDescription = "search", tint = arrowColor)
    }
}

@Preview
@Composable
private fun PreviewDarkPageContainerWithBackButton() {
    GronurGroceryTheme {
        DarkPageContainerWithBackButton(
            putMask = {},
            onContainerClick = {},
            onBackButtonPressed = {},
            title = "Fruits",
            preContent = {
                Column(modifier = Modifier.height(1.dp)) {

                }
            },
            qrIconAction = {},
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {

                }
            }
        )
    }
}