package com.example.gronurgrocery.features.home.domain.use_cases.home_main

import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.home.data.source.remote.dto.toDomain
import com.example.gronurgrocery.features.home.domain.model.Product
import com.example.gronurgrocery.features.home.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading())
            val categoryList = repository.getCategories().map { it.toDomain() }
            emit(Resource.Success(categoryList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, please check your internet connection"))
        }
    }
}