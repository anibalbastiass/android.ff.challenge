package com.ff.challenge.app.domain.usecase

import com.ff.challenge.app.domain.repository.CacheRepository

class SignOutUseCase(
    private val repository: CacheRepository
) {
    suspend fun execute() = repository.clearAll()
}
