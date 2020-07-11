package com.ff.challenge.feature.auth.domain.usecase

import com.ff.challenge.feature.auth.domain.model.DomainUser
import com.ff.challenge.feature.auth.domain.repository.LocalRepository

internal class GetSignedUserUseCase(
    private val moviesRepository: LocalRepository
) {
    suspend fun execute(email: String, password: String): DomainUser? =
        moviesRepository.getUserByEmailPassword(email, password)
}
