package com.example.gronurgrocery.common.domain.use_cases

import com.example.gronurgrocery.common.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadUserTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    operator fun invoke(): Flow<String?> {
        return dataStoreRepository.readUserToken()
    }
}