package com.example.gronurgrocery.features.profile.presentation.address

import com.example.gronurgrocery.R
import com.example.gronurgrocery.features.profile.domain.model.Address
import com.example.gronurgrocery.features.profile.domain.model.AddressType

data class DeliveryAddressState(
    val addresses: List<Address> = listOf(
        Address(
            addressLine = "18, elbana buildings, Street 100, New Damietta",
            addressType = AddressType(
                typeName = "Home",
                typeIconRes = R.drawable.home
            ),
            city = "Damietta",
            country = "Egypt"
        ),
        Address(
            addressLine = "18, elbana buildings, Street 100, New Damietta",
            addressType = AddressType(
                typeName = "Home 01",
                typeIconRes = R.drawable.home
            ),
            city = "Damietta",
            country = "Egypt"
        ),
        Address(
            addressLine = "18, elbana buildings, Street 100, New Damietta",
            addressType = AddressType(
                typeName = "Work",
                typeIconRes = R.drawable.profile
            ),
            city = "Damietta",
            country = "Egypt"
        )
    ),

    val newAddress: Address = Address(
        addressType = AddressType(
            typeName = "",
            typeIconRes = R.drawable.location
        ),
        addressLine = "",
        city = "",
        country = ""
    ),

    val isCountryExpanded: Boolean = true,

    val countries: List<String> = listOf(
        "Egypt",
        "Misr",
        "Om eldonya"
    ),

    val countryChoice: String? = null
)

/*

* */