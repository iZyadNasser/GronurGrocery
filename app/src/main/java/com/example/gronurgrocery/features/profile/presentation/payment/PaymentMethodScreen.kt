package com.example.gronurgrocery.features.profile.presentation.payment

import androidx.compose.animation.animateContentSize
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
import com.example.gronurgrocery.features.profile.domain.model.Payment
import com.example.gronurgrocery.features.profile.domain.model.toDateString
import com.example.gronurgrocery.features.ui.theme.GronurGroceryTheme
import com.example.gronurgrocery.features.ui.theme.background

@Composable
fun PaymentMethod(
    onUpButtonPressed: () -> Unit,
    modifier: Modifier = Modifier,
    paymentMethodViewModel: PaymentMethodViewModel = viewModel<PaymentMethodViewModel>()
) {

    val uiState = paymentMethodViewModel.state.value

    DarkPageContainerWithBackButton(
        onBackButtonPressed = { onUpButtonPressed() },
        title = "Payment Method",
        preContent = { },
        qrIconAction = { /* TODO */ },
        content = {
            PaymentMethodScreen(
                payments = uiState.payments
            )
        }
    )
}

@Composable
private fun PaymentMethodScreen(
    modifier: Modifier = Modifier,
    payments: List<Payment> = emptyList(),
) {
    if (payments.isEmpty()) {
        NoPaymentScreen()
    } else {
        HasPaymentScreen(payments)
    }
}

@Composable
private fun NoPaymentScreen(
    modifier: Modifier = Modifier,
    paymentMethodViewModel: PaymentMethodViewModel = viewModel<PaymentMethodViewModel>()
) {

    val uiState = paymentMethodViewModel.state.value

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
                    painter = painterResource(id = R.drawable.card_empty),
                    contentDescription = "payment methods list is empty",
                    modifier = Modifier
                        .size(205.dp)
                )

                Spacer(modifier = Modifier.height(44.dp))

                Text(
                    text = "Card Empty",
                    color = background,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "It appears that you haven't acquired a credit or debit card as of yet.",
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
                text = "Add Card",
                onClick = { paymentMethodViewModel.toggleBottomSheet() },
                modifier = Modifier
                    .align(Alignment.BottomStart)
            )

            if (uiState.isBottomSheetOpen) {
                AddCardSheet(
                    onDismiss = { paymentMethodViewModel.toggleBottomSheet() },
                    paymentMethodViewModel = paymentMethodViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCardSheet(
    onDismiss: () -> Unit,
    paymentMethodViewModel: PaymentMethodViewModel = viewModel<PaymentMethodViewModel>()
) {

    val uiState = paymentMethodViewModel.state.value

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        modifier = Modifier

    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .padding(
                    top = 20.dp,
                    bottom = 36.dp,
                    start = 20.dp,
                    end = 20.dp
                )
                .animateContentSize()
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Add a Card",
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
                            .clickable {
                                paymentMethodViewModel.toggleBottomSheet()
                            }
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
                    label = "Card number",
                    iconDrawable = R.drawable.credit_card,
                    fieldValue = uiState.newCard.cardNumber,
                    onValueChange = { paymentMethodViewModel.updateCardNumber(it) },
                    iconTint = background,
                    placeholderColor = background,
                    visibilityIconDrawable = if (uiState.isCardNumValid) R.drawable.card_num_valid else null,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    /* TODO(Add date validation and help typing it) */
                    FormTextField(
                        label = "MM/YY",
                        fieldValue = uiState.newCard.date.toDateString(),
                        onValueChange = { paymentMethodViewModel.updateCardDate(it) },
                        iconTint = Color(0xFF96A4B2),
                        placeholderColor = Color(0xFF96A4B2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    FormTextField(
                        label = "CVC",
                        fieldValue = uiState.newCard.cvc,
                        onValueChange = { paymentMethodViewModel.updateCVC(it) },
                        iconTint = Color(0xFF96A4B2),
                        placeholderColor = Color(0xFF96A4B2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }

            FormButton(
                text = "Add Card",
                onClick = {
                    paymentMethodViewModel.addNewCard()
                    paymentMethodViewModel.toggleBottomSheet()
                }
            )
        }
    }
}

@Composable
private fun HasPaymentScreen(
    payments: List<Payment>,
    modifier: Modifier = Modifier,
    paymentMethodViewModel: PaymentMethodViewModel = viewModel<PaymentMethodViewModel>()
) {

    val uiState = paymentMethodViewModel.state.value

    Box {
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

                items(payments) { method ->
                    PaymentMethodItem(paymentMethod = method)
                }
            }

            FormButton(
                text = "Add Address",
                onClick = { paymentMethodViewModel.toggleBottomSheet() }
            )
        }

        if (uiState.isBottomSheetOpen) {
            AddCardSheet(
                onDismiss = { paymentMethodViewModel.toggleBottomSheet() },
                paymentMethodViewModel = paymentMethodViewModel
            )
        }
    }
}

@Composable
private fun PaymentMethodItem(
    paymentMethod: Payment,
    modifier: Modifier = Modifier
) {

    Text(text = paymentMethod.cardNumber)

    /* TODO(Design card item) */
}

@Preview
@Composable
private fun PreviewPaymentMethod() {
    GronurGroceryTheme {
        PaymentMethod(onUpButtonPressed = {})
    }
}