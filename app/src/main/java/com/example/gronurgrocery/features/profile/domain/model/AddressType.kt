package com.example.gronurgrocery.features.profile.domain.model

import androidx.annotation.DrawableRes
import com.example.gronurgrocery.R

data class AddressType(
    val typeName: String,
    @DrawableRes val typeIconRes: Int = R.drawable.home
)
