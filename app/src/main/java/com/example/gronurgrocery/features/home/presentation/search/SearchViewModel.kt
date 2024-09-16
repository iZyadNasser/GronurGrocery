package com.example.gronurgrocery.features.home.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun updateSearchBarState(newText: String) {
        _state.value = _state.value.copy(
            currentSearchText = newText
        )
    }
}