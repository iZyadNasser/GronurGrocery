package com.example.gronurgrocery.features.home.presentation.main

import com.example.gronurgrocery.features.home.domain.model.Product

data class HomeMainState(
    val productList: List<Product> = emptyList(),
    val dataIsLoading: Boolean = false,
    val dataError: String = "",
    val category: String = "Fruits",
    val categoryList: List<String> = emptyList(),
    val categoryDataIsLoading: Boolean = false,
    val categoryDataError: String = ""
)