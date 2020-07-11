package com.ff.challenge.app.domain.usecase

import com.ff.challenge.app.data.repository.CacheRepositoryImpl
import com.ff.challenge.library.testutils.foundation.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetSessionUseCaseTest {

    @MockK
    internal lateinit var mockRepository: CacheRepositoryImpl

    private lateinit var cut: GetSessionUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetSessionUseCase(mockRepository)
    }

    @Test
    fun `return boolean`() {
        // given
        val data = RandomFactory.generateString()
        coEvery { mockRepository.getSession() } returns data

        // when
        val result = runBlocking { cut.execute() }

        // then
        result shouldBeEqualTo data
    }
}