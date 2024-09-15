package com.example.gronurgrocery.features.home.presentation.category_products

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.home.domain.use_cases.category_products.GetAllProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryProductsViewModel @Inject constructor(
    private val getAllProductsByCategoryUseCase: GetAllProductsByCategoryUseCase
): ViewModel() {

    private val _state = mutableStateOf(CategoryProductsState())
    val state: State<CategoryProductsState> = _state

    init {
        getCurrentCategoryProducts()
    }

    private fun getCurrentCategoryProducts() {
        getAllProductsByCategoryUseCase(_state.value.category).onEach { productResponse ->
            when (productResponse) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isDataLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isDataLoading = false,
                        isDataError = productResponse.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isDataLoading = false,
                        productList = productResponse.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}