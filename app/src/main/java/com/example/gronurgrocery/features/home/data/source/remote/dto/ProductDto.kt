package com.example.gronurgrocery.features.home.data.source.remote.dto

import com.example.gronurgrocery.features.home.domain.model.Product

data class ProductDto(
    // TODO
    val imageUrl: String,
    val name: String,
    val cals: String,
    val price: String
)

fun ProductDto.toDomain(): Product {
    return Product(
        imageUrl = imageUrl,
        name = name,
        cals = cals,
        price = price
    )
}
