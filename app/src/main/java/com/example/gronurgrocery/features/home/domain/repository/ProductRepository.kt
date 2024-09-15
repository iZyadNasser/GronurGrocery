package com.example.gronurgrocery.features.home.domain.repository

import com.example.gronurgrocery.features.home.data.source.remote.dto.CategoryDto
import com.example.gronurgrocery.features.home.data.source.remote.dto.ProductDto

interface ProductRepository {

    suspend fun getCategories(): List<CategoryDto>

    suspend fun getPopularProductsByCategory(category: String): List<ProductDto>

    suspend fun getAllProductsByCategory(category: String): List<ProductDto>
}