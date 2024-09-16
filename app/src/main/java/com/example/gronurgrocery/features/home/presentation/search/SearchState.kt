package com.example.gronurgrocery.features.home.presentation.search

import com.example.gronurgrocery.features.home.domain.model.Product

data class SearchState(
    val items: List<Product> = emptyList(),
    val currentSearchText: String = "",
    val isFilterTabOpen: Boolean = false
)
