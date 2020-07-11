package com.ff.challenge.feature.auth.domain.mapper

import com.ff.challenge.feature.auth.factory.UserFactory
import junit.framework.Assert.assertEquals
import org.junit.Test

class LocalUserMapperTest {

    private val mapper = LocalUserMapper()

    @Test
    fun `given EntityUser, when fromEntityToDomain, then DomainUser`() {
        val entity = UserFactory.makeEntityUser()
        val domain = with(mapper) { entity.fromLocalToDomain() }

        assertEquals("userId", entity.userId, domain.userId)
        assertEquals("fullName", entity.fullName, domain.fullName)
        assertEquals("email", entity.email, domain.email)
        assertEquals("password", entity.password, domain.password)
    }
}