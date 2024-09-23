package com.example.gronurgrocery.features.profile.domain.use_case

import com.example.gronurgrocery.common.domain.repository.DataStoreRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke() {
        dataStoreRepository.saveUserToken("")
    }
}