package com.example.gronurgrocery.features.home.presentation.product_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ProductDetailViewModel: ViewModel() {

    private val _state = mutableStateOf(ProductDetailState())
    val state: State<ProductDetailState> = _state

    fun initializeMaxAmount(maxAmount: Int) {
        _state.value = _state.value.copy(
            maxAmount = maxAmount
        )
    }

    fun changeAmount(isInc: Boolean) {
        if (isInc && _state.value.currentAmount < _state.value.maxAmount) {
            _state.value = _state.value.copy(
                currentAmount = _state.value.currentAmount + 1
            )
        }

        if (!isInc && _state.value.currentAmount > 1) {
            _state.value = _state.value.copy(
                currentAmount = _state.value.currentAmount - 1
            )
        }
    }

}