package com.ff.challenge.feature.auth.domain.mapper

import com.ff.challenge.feature.auth.domain.local.model.EntityUser
import com.ff.challenge.feature.auth.domain.model.DomainUser

class LocalUserMapper {

    fun EntityUser.fromLocalToDomain() = DomainUser(
        userId = userId,
        fullName = fullName,
        email = email,
        password = password
    )
}