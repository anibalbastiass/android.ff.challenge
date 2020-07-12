package com.ff.challenge.app.domain.usecase

import com.ff.challenge.app.data.repository.CacheRepositoryImpl
import com.ff.challenge.library.testutils.foundation.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StoreSessionUseCaseTest {

    @MockK
    internal lateinit var mockRepository: CacheRepositoryImpl

    private lateinit var cut: StoreSessionUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = StoreSessionUseCase(mockRepository)
    }

    @Test
    fun `return boolean`() {
        // given
        val data = RandomFactory.generateString()
        coEvery { mockRepository.storeSession(data) } answers { mockk() }

        // when
        runBlocking { cut.execute(data) }

        // then
        coVerify { mockRepository.storeSession(data) }
    }
}