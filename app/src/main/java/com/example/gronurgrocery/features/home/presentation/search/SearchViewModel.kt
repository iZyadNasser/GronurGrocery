package com.example.gronurgrocery.features.home.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.home.presentation.search.search_state.Filter
import com.example.gronurgrocery.features.home.presentation.search.search_state.PriceRange
import com.example.gronurgrocery.features.home.presentation.search.search_state.SearchState

class SearchViewModel: ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    init {
        getCategories()
    }

    fun updateSearchBarState(newText: String) {
        _state.value = _state.value.copy(
            currentSearchText = newText
        )
    }

    fun toggleFilterTab() {
        _state.value = _state.value.copy(
            isFilterTabOpen = !_state.value.isFilterTabOpen
        )
    }

    fun resetFilters() {
        _state.value = _state.value.copy(
            searchFilter = Filter(
                recentSearch = _state.value.recentSearch,
                categoriesChoice = mutableSetOf()
            )
        )
        getCategories()
    }

    fun updatePriceRangeState(priceRange: PriceRange) {
        _state.value = _state.value.copy(
            searchFilter = _state.value.searchFilter.copy(
                priceRange = priceRange
            )
        )
    }

    fun updateCurrentCategories(newCategory: String) {
        val newSet = _state.value.searchFilter.categoriesChoice
        if (!newSet.contains(newCategory)) {
            newSet.add(newCategory)
        } else {
            newSet.remove(newCategory)
        }
        _state.value = _state.value.copy(
            searchFilter = _state.value.searchFilter.copy(
                categoriesChoice = newSet
            )
        )

        forceRecomposition()
    }

    private fun getCategories() {
        // TODO
        _state.value = _state.value.copy(
            searchFilter = _state.value.searchFilter.copy(
                categories = listOf(
                    "Fruits",
                    "Fast-food",
                    "Vegetables",
                    "Fish"
                )
            )
        )
    }

    fun forceRecomposition() {
        _state.value = _state.value.copy(
            recompositionTrigger = !_state.value.recompositionTrigger
        )
    }
}