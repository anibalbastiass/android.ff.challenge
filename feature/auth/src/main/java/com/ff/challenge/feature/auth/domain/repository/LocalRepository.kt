package com.ff.challenge.feature.auth.domain.repository

import com.ff.challenge.feature.auth.domain.model.DomainUser

interface LocalRepository {
    suspend fun getUserByEmailPassword(email: String, password: String): DomainUser?
}
