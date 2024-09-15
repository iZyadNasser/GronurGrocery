package com.example.gronurgrocery.features.home.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.home.domain.use_cases.home_main.GetCategoriesUseCase
import com.example.gronurgrocery.features.home.domain.use_cases.home_main.GetPopularProductsByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    private val getPopularProductsByCategoryUseCase: GetPopularProductsByCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {

    private val _state = mutableStateOf(HomeMainState())
    val state: State<HomeMainState> = _state

    init {
        getCategories()
        getCurrentCategoryPopularProducts()
    }

    private fun getCategories() {
        getCategoriesUseCase().onEach { categoryResponse ->
            when (categoryResponse) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(categoryDataIsLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        categoryDataIsLoading = false,
                        dataError = categoryResponse.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        categoryDataIsLoading = false,
                        categoryList = categoryResponse.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateCurrentCategory(newCategory: String) {
        _state.value = _state.value.copy(
            category = newCategory
        )

        getCurrentCategoryPopularProducts()
    }

    private fun getCurrentCategoryPopularProducts() {
        getPopularProductsByCategoryUseCase(_state.value.category).onEach { productResponse ->
            when (productResponse) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(dataIsLoading = true)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        dataIsLoading = false,
                        dataError = productResponse.message ?: "An unexpected error happened"
                    )
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        dataIsLoading = false,
                        productList = productResponse.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}