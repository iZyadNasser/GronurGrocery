package com.example.gronurgrocery.features.home.presentation.search.search_state

data class Filter(
    val priceRange: PriceRange = PriceRange(),
    val categories: List<String> = emptyList(),
    val categoriesChoice: MutableSet<String> = mutableSetOf(),
    val recentSearch: List<String> = emptyList(),
    val recentSearchChoice: String? = null
)