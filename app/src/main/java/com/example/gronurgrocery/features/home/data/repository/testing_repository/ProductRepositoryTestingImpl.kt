package com.example.gronurgrocery.features.home.data.repository.testing_repository

import com.example.gronurgrocery.features.home.data.source.remote.dto.CategoryDto
import com.example.gronurgrocery.features.home.data.source.remote.dto.ProductDto
import com.example.gronurgrocery.features.home.data.source.testing_source.CategoriesTestingSource
import com.example.gronurgrocery.features.home.data.source.testing_source.ProductsTestingSource
import com.example.gronurgrocery.features.home.domain.repository.ProductRepository

class ProductRepositoryTestingImpl : ProductRepository {
    override suspend fun getCategories(): List<CategoryDto> {
        return CategoriesTestingSource.categories
    }

    override suspend fun getPopularProductsByCategory(category: String): List<ProductDto> {
        return when {
            category.lowercase() == "fruits" -> {
                ProductsTestingSource.fruits
            }
            category.lowercase() == "vegetables" -> {
                ProductsTestingSource.vegetables
            }
            else -> {
                ProductsTestingSource.fastFood
            }
        }
    }

    override suspend fun getAllProductsByCategory(category: String): List<ProductDto> {
        return when {
            category.lowercase() == "fruits" -> {
                ProductsTestingSource.fruits
            }
            category.lowercase() == "vegetables" -> {
                ProductsTestingSource.vegetables
            }
            else -> {
                ProductsTestingSource.fastFood
            }
        }
    }
}