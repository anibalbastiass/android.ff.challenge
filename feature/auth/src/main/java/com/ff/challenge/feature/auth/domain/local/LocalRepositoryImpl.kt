package com.ff.challenge.feature.auth.domain.local

import com.ff.challenge.feature.auth.data.local.AuthDatabase
import com.ff.challenge.feature.auth.domain.mapper.LocalUserMapper
import com.ff.challenge.feature.auth.domain.model.DomainUser
import com.ff.challenge.feature.auth.domain.repository.LocalRepository

open class LocalRepositoryImpl(
    private val mapper: LocalUserMapper,
    private val database: AuthDatabase
) : LocalRepository {

    override suspend fun getUserByEmailPassword(email: String, password: String): DomainUser? =
        with(mapper) {
            database.users.getUserByEmailPassword(email, password).fromLocalToDomain()
        }

}
