package com.example.gronurgrocery.features.home.domain.model

data class ProductDetail(
    val imageUrl: String,
    val name: String,
    val isAvailable: Boolean,
    val maxAmount: Int,
    val description: String,
    val productReviews: List<Review>,
    val similarProducts: List<Product>,
    val price: String
)
