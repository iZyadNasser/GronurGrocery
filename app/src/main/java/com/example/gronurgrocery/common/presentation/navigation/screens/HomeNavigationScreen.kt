package com.example.gronurgrocery.common.presentation.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
data class CategoryProductsList(
    val token: String,
    val category: String
)

@Serializable
data class ProductDetail(
    val token: String,
    val productId: String
)

@Serializable
data class Search(
    val token: String
)