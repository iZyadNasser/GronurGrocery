package com.example.gronurgrocery.features.home.presentation.search.search_state

import com.example.gronurgrocery.features.home.domain.model.Product

data class SearchState(
    val items: List<Product> = emptyList(),
    val currentSearchText: String = "",
    val isFilterTabOpen: Boolean = true,
    val recentSearch: List<String> = emptyList(),
    val searchFilter: Filter = Filter(recentSearch = recentSearch)
)
