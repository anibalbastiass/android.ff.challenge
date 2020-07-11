package com.ff.challenge.feature.auth.factory

import com.ff.challenge.feature.auth.domain.local.model.EntityUser
import com.ff.challenge.feature.auth.domain.model.DomainUser
import com.ff.challenge.library.testutils.foundation.RandomFactory.generateString

object UserFactory {

    fun makeEntityUser() = EntityUser(
        userId = generateString(),
        fullName = generateString(),
        email = generateString(),
        password = generateString()
    )

    fun makeDomainUser() = DomainUser(
        userId = generateString(),
        fullName = generateString(),
        email = generateString(),
        password = generateString()
    )
}