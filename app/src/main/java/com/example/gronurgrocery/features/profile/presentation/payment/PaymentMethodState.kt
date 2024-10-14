package com.example.gronurgrocery.features.profile.presentation.payment

import com.example.gronurgrocery.features.profile.domain.model.Payment
import com.example.gronurgrocery.features.profile.domain.model.PaymentDate

data class PaymentMethodState(
    val payments: List<Payment> = emptyList(),
    val isBottomSheetOpen: Boolean = false,
    val newCard: Payment = Payment(
        cardNumber = "",
        date = PaymentDate(
            month = "",
            year = ""
        ),
        cvc = ""
    ),
    val isCardNumValid: Boolean = false
)
