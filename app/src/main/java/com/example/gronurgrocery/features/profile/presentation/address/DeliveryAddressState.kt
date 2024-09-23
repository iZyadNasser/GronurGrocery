package com.example.gronurgrocery.features.profile.presentation.address

import com.example.gronurgrocery.features.profile.domain.model.Address

data class DeliveryAddressState(
    val addresses: List<Address> = emptyList()
)