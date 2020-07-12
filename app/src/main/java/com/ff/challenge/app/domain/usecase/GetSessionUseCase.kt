package com.ff.challenge.app.domain.usecase

import com.ff.challenge.app.domain.repository.CacheRepository

class GetSessionUseCase(
    private val repository: CacheRepository
) {
    suspend fun execute(): String = repository.getSession()
}
