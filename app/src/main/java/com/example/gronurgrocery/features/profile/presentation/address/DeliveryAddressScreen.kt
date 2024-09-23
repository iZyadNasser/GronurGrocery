package com.example.gronurgrocery.features.profile.presentation.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gronurgrocery.R
import com.example.gronurgrocery.common.presentation.ui.components.DarkPageContainerWithBackButton
import com.example.gronurgrocery.common.presentation.ui.components.FormButton
import com.example.gronurgrocery.features.profile.domain.model.Address
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun DeliveryAddress(
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    deliveryAddressViewModel: DeliveryAddressViewModel = viewModel<DeliveryAddressViewModel>()
) {

    val uiState = deliveryAddressViewModel.state.value

    DarkPageContainerWithBackButton(
        onBackButtonPressed = { onUpButtonPressed() },
        title = "Delivery Address",
        preContent = { },
        content = {
            DeliveryAddressScreen(
                addresses = uiState.addresses
            )
        }
    )
}

@Composable
private fun DeliveryAddressScreen(
    modifier: Modifier = Modifier,
    addresses: List<Address> = emptyList(),
) {
    if (addresses.isEmpty()) {
        NoAddressScreen()
    } else {
        HasAddressScreen()
    }
}

@Composable
private fun NoAddressScreen(
    modifier: Modifier = Modifier
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
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(32.dp)
                )
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.address_empty),
                    contentDescription = "Address list is empty",
                    modifier = Modifier
                        .size(205.dp)
                )

                Spacer(modifier = Modifier.height(44.dp))

                Text(
                    text = "Address Empty",
                    color = background,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "It appears that you haven't acquired a delivery address as of yet.",
                    color = Color(0xFF96A4B2),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.height(100.dp))
            }

            FormButton(
                text = "Add Address",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun HasAddressScreen(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
private fun PreviewDeliveryAddress() {
    GronurGroceryTheme {
        DeliveryAddress(onUpButtonPressed = { /*TODO*/ })
    }
}