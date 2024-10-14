package com.example.gronurgrocery.features.profile.domain.model

data class Payment(
    val cardNumber: String,
    val date: PaymentDate,
    val cvc: String
)
