package com.example.gronurgrocery.features.home.presentation.category_products

import com.example.gronurgrocery.features.home.domain.model.Product

data class CategoryProductsState(
    val category: String = "",
    val isDataLoading: Boolean = false,
    val isDataError: String = "",
    val productList: List<Product> = emptyList()
)
