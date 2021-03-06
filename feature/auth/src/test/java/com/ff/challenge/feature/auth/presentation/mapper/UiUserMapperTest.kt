package com.ff.challenge.feature.auth.presentation.mapper

import com.ff.challenge.feature.auth.factory.UserFactory
import com.ff.challenge.feature.auth.presentation.model.UiUser
import junit.framework.Assert.assertEquals
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class UiUserMapperTest {

    private val mapper = UiUserMapper()

    @Test
    fun `given DomainUser, when fromDomainToUi, then UiUser`() {
        val domain = UserFactory.makeDomainUser()
        val ui = with(mapper) { domain.fromDomainToUi() }

        assertEquals("userId", domain.userId, ui.userId)
        assertEquals("fullName", domain.fullName, ui.fullName)
        assertEquals("email", domain.email, ui.email)
        assertEquals("password", domain.password, ui.password)

        ui shouldBeEqualTo UiUser(
            ui.userId,
            ui.fullName,
            ui.email,
            ui.password
        )
    }
}