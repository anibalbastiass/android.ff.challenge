package com.ff.challenge.feature.currencies.domain.usecase

import com.ff.challenge.feature.currencies.data.repository.RemoteRepositoryImpl
import com.ff.challenge.feature.currencies.factory.CurrenciesFactory
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
class GetCurrencyListUseCaseTest {

    @MockK
    internal lateinit var mockRepository: RemoteRepositoryImpl

    private lateinit var cut: GetCurrencyListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetCurrencyListUseCase(mockRepository)
    }

    @Test
    fun `return boolean`() {
        // given
        val data = CurrenciesFactory.makeDomainCurrencies()
        coEvery { mockRepository.getCurrenciesList() } returns data

        // when
        val result = runBlocking { cut.execute() }

        // then
        result shouldBeEqualTo data
    }

}