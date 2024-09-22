package com.example.gronurgrocery.features.auth.domain.use_case

import com.example.gronurgrocery.common.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveUserTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend operator fun invoke(token: String?) {
        dataStoreRepository.saveUserToken(token)
    }
}