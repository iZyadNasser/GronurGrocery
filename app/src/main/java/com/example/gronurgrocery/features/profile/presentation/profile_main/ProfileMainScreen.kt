package com.example.gronurgrocery.features.profile.presentation.profile_main

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.DarkPageContainerWithBackButton
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun ProfileMain(
    onBackButtonPressed: () -> Unit,
    onLogoutPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    DarkPageContainerWithBackButton(
        onBackButtonPressed = { onBackButtonPressed() },
        title = "Profile",
        preContent = { },
        content = {
            ProfileMainScreen(onLogoutPressed = onLogoutPressed)
        }
    )
}

@Composable
private fun ProfileMainScreen(
    modifier: Modifier = Modifier,
    onLogoutPressed: () -> Unit,
    profileMainViewModel: ProfileMainViewModel = hiltViewModel()
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .fillMaxSize()
            .background(Color(0xFFF4F5F7))
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    tint = Color.Gray,
                    modifier = Modifier
                        .clip(RoundedCornerShape(45.dp))
                        .size(90.dp)
                        .background(Color(0xFFF4F5F7))
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Shahinur Rahman", /* TODO( get from api ) */
                    color = background,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "shahinurstk02@gmail.com", /* TODO( get from api ) */
                    color = Color(0xFF96A4B2),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                ProfileButton(
                    onClick = { /*TODO*/ },
                    text = "Account Information",
                    icon = R.drawable.frame
                )
                Spacer(modifier = Modifier.height(16.dp))

                ProfileButton(
                    onClick = { /*TODO*/ },
                    text = "Delivery Address",
                    icon = R.drawable.location
                )
                Spacer(modifier = Modifier.height(16.dp))

                ProfileButton(
                    onClick = { /*TODO*/ },
                    text = "Payment Method",
                    icon = R.drawable.empty_wallet
                )
                Spacer(modifier = Modifier.height(16.dp))

                ProfileButton(
                    onClick = { /*TODO*/ },
                    text = "Password",
                    icon = R.drawable.profile_lock
                )
                Spacer(modifier = Modifier.height(16.dp))

                ProfileButton(
                    onClick = { /*TODO*/ },
                    text = "Reference Friends",
                    icon = R.drawable.friends
                )
            }

            Spacer(modifier = Modifier.height(68.dp))
            FormButton(
                text = "Log out",
                onClick = {
                    profileMainViewModel.onLogoutClick()
                    onLogoutPressed()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProfileButton(
    onClick: () -> Unit,
    text: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick() }
            .background(Color(0xFFF8F8F8))
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = background
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    color = background
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.profile_arrow),
                contentDescription = "go",
                tint = background
            )
        }
    }
}

@Preview
@Composable
private fun PreviewProfileButton() {
    GronurGroceryTheme {
        ProfileButton(
            onClick = { },
            text = "Account Information",
            icon = R.drawable.profile
        )
    }
}

@Preview
@Composable
private fun PreviewProfileMain() {
    GronurGroceryTheme {
        ProfileMain(
            onBackButtonPressed = {},
            onLogoutPressed = {}
        )
    }
}