package com.ff.challenge.app.domain.usecase

import com.ff.challenge.app.domain.repository.CacheRepository

class StoreSessionUseCase(
    private val repository: CacheRepository
) {
    suspend fun execute(data: String) = repository.storeSession(data)
}
