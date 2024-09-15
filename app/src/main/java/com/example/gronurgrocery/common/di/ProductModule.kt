package com.example.gronurgrocery.common.di

import com.example.gronurgrocery.features.home.data.repository.testing_repository.ProductRepositoryTestingImpl
import com.example.gronurgrocery.features.home.domain.repository.ProductRepository
import com.example.gronurgrocery.features.home.domain.use_cases.home_main.GetPopularProductsByCategoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryTestingImpl()
    }

    @Provides
    @Singleton
    fun provideGetPopularProductByCategoryUseCase(productRepository: ProductRepository): GetPopularProductsByCategoryUseCase {
        return GetPopularProductsByCategoryUseCase(productRepository)
    }
}