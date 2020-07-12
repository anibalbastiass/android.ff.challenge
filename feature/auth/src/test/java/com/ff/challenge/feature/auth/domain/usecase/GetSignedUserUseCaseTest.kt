package com.ff.challenge.feature.auth.domain.usecase

import com.ff.challenge.feature.auth.domain.repository.LocalRepository
import com.ff.challenge.feature.auth.factory.UserFactory
import com.ff.challenge.library.testutils.foundation.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.any
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetSignedUserUseCaseTest {

    @MockK
    internal lateinit var mockRepository: LocalRepository

    private lateinit var cut: GetSignedUserUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetSignedUserUseCase(mockRepository)
    }

    @Test
    fun `return DomainUser`() {
        // given
        val email = RandomFactory.generateRandomValidEmail()
        val password = RandomFactory.generateString()
        val domain = UserFactory.makeDomainUser()
        coEvery { mockRepository.getUserByEmailPassword(email, password) } returns domain

        // when
        val result = runBlocking { cut.execute(email, password) }

        // then
        result shouldBeEqualTo domain
    }
}