package com.example.gronurgrocery.common.presentation.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
data class CategoryProductsList(
    val category: String
)

@Serializable
data class ProductDetail(
    val productId: String
)

@Serializable
object Search