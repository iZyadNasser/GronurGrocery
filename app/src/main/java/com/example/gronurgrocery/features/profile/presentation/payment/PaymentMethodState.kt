package com.example.gronurgrocery.features.profile.presentation.payment

import com.example.gronurgrocery.features.profile.domain.model.Payment

data class PaymentMethodState(
    val payments: List<Payment> = emptyList()
)
