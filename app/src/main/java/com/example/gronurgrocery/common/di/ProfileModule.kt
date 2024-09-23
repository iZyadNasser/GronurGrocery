package com.example.gronurgrocery.common.di

import com.example.gronurgrocery.common.domain.repository.DataStoreRepository
import com.example.gronurgrocery.features.profile.domain.use_case.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideLogoutUseCase(dataStoreRepository: DataStoreRepository): LogoutUseCase {
        return LogoutUseCase(dataStoreRepository)
    }
}