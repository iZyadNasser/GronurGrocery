package com.example.gronurgrocery.features.profile.presentation.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.gronurgrocery.common.presentation.ui.components.FormTextField
import com.example.gronurgrocery.features.auth.presentation.common.components.FormDivider
import com.example.gronurgrocery.features.profile.domain.model.Address
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun DeliveryAddress(
    onUpButtonPressed: () -> Unit,
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
    addresses: List<Address> = emptyList()
) {
    if (addresses.isEmpty()) {
        NoAddressScreen()
    } else {
        AddAddressSheet(onDismiss = { /*TODO*/ })
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
    addresses: List<Address>,
    modifier: Modifier = Modifier,
    deliveryAddressViewModel: DeliveryAddressViewModel = viewModel<DeliveryAddressViewModel>()
) {

    val uiState = deliveryAddressViewModel.state.value

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 32.dp,
                    topEnd = 32.dp
                )
            )
            .fillMaxSize()
            .background(Color(0xFFF4F5F7))
            .padding(
                bottom = 36.dp,
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
            .navigationBarsPadding()
    ) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)

        ) {

            items(addresses) { address ->
                DeliveryAddressItem(address = address)
            }
        }

        FormButton(
            text = "Add Address",
            onClick = { /*TODO*/ }
        )
    }
}

@Composable
private fun DeliveryAddressItem(
    address: Address,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(
                top = 24.dp,
                bottom = 24.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = address.addressType.typeIconRes),
                contentDescription = address.addressType.typeName,
                tint = background
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = address.addressType.typeName,
                color = background,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${address.addressLine}, ${address.city}, ${address.country}",
            color = Color(0xFF96A4B2),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddAddressSheet(
    onDismiss: () -> Unit,
    deliveryAddressViewModel: DeliveryAddressViewModel = viewModel<DeliveryAddressViewModel>()
) {

    val uiState = deliveryAddressViewModel.state.value

    Box(
        //onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 20.dp,
                    bottom = 36.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Add a Address",
                    color = background,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 22.sp,
                    )
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(40.dp)
                        .background(Color(0xFFF8F8F8))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.decline),
                        contentDescription = "close"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                color = Color(0xFF96A4B2),
                modifier = Modifier
                    .alpha(0.1f)
            )
            Spacer(modifier = Modifier.height(32.dp))

            FormTextField(
                onClick = { deliveryAddressViewModel.toggleCountryMenu() },
                label = "Location type",
                iconDrawable = R.drawable.location,
                fieldValue = uiState.newAddress.addressType.typeName,
                onValueChange = { deliveryAddressViewModel.updateAddressType(it) },
                iconTint = background,
                placeholderColor = background,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            deliveryAddressViewModel.toggleCountryMenu()
                        }
                ) {
                    FormTextField(
                        readOnly = true,
                        label = "Country",
                        fieldValue = uiState.countryChoice ?: "",
                        onValueChange = { },
                        iconTint = Color(0xFF96A4B2),
                        placeholderColor = Color(0xFF96A4B2),
                        visibilityIconDrawable = if (uiState.isCountryExpanded) R.drawable.baseline_expand_less_24 else R.drawable.baseline_expand_more_24,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                deliveryAddressViewModel.toggleCountryMenu()
                            }

                    )

                    DropdownMenu(
                        expanded = uiState.isCountryExpanded,
                        onDismissRequest = { deliveryAddressViewModel.toggleCountryMenu() },
                    ) {
                        uiState.countries.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    deliveryAddressViewModel.changeCountryChoice(item)
                                    deliveryAddressViewModel.closeCountryMenu()
                                }
                            )
                        }
                    }

                    Box(modifier = Modifier.fillMaxWidth().height(20.dp))
                }

                FormTextField(
                    label = "City",
                    fieldValue = uiState.newAddress.addressType.typeName,
                    onValueChange = { deliveryAddressViewModel.updateAddressType(it) },
                    iconTint = Color(0xFF96A4B2),
                    placeholderColor = Color(0xFF96A4B2),
                    visibilityIconDrawable = if (uiState.isCountryExpanded) R.drawable.baseline_expand_less_24 else R.drawable.baseline_expand_more_24,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAddAddressSheet() {
    GronurGroceryTheme {
        AddAddressSheet(
            onDismiss = {}
        )
    }
}

@Preview
@Composable
private fun PreviewDeliveryAddress() {
    GronurGroceryTheme {
        DeliveryAddress(onUpButtonPressed = { /*TODO*/ })
    }
}