package com.ff.challenge.app.domain.usecase

import com.ff.challenge.app.data.repository.CacheRepositoryImpl
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
class SignOutUseCaseTest {

    @MockK
    internal lateinit var mockRepository: CacheRepositoryImpl

    private lateinit var cut: SignOutUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = SignOutUseCase(mockRepository)
    }

    @Test
    fun `return boolean`() {
        // given
        coEvery { mockRepository.clearAll() } answers { mockk() }

        // when
        runBlocking { cut.execute() }

        // then
        coVerify { mockRepository.clearAll() }
    }
}