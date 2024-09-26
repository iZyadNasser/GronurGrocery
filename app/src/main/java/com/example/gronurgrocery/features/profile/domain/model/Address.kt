package com.example.gronurgrocery.features.profile.domain.model

data class Address(
    val addressType: AddressType,
    val country: String,
    val city: String,
    val addressLine: String
)
