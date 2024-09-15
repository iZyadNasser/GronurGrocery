package com.example.gronurgrocery.common.presentation.ui.components.category_row

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CategoryRowViewModel: ViewModel() {

    private val _state = mutableStateOf(CategoryRowState())
    val state: State<CategoryRowState> = _state

    private var isFirst = true

    fun initializeSelectedItem(newItem: String?) {
        if (isFirst) {
            updateSelectedItem(newItem)
            isFirst = false
        }
    }

    fun updateSelectedItem(newSelectedItem: String?) {
        _state.value = _state.value.copy(
            selectedItem = newSelectedItem
        )
    }

}