package com.ff.challenge.feature.currencies.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ff.challenge.feature.currencies.domain.usecase.GetCurrencyListUseCase
import com.ff.challenge.feature.currencies.factory.CurrenciesFactory
import com.ff.challenge.feature.currencies.presentation.mapper.UiCurrenciesMapper
import com.ff.challenge.feature.currencies.presentation.viewstate.CurrencyListViewState
import com.ff.challenge.library.testutils.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CurrencyListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var useCase: GetCurrencyListUseCase
    private lateinit var mapper: UiCurrenciesMapper
    private lateinit var cut: CurrencyListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = UiCurrenciesMapper()
        cut = CurrencyListViewModel(mapper, useCase)
    }

    @Test
    fun `verify state when GetCurrencyListUseCase fails`() {
        // given
        coEvery { useCase.execute() }

        // when
        cut.postErrorAction()

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs CurrencyListViewState.CurrencyListFailure
    }

    @Test
    fun `verify state when GetCurrencyListUseCase`() {
        // given
        val domain = CurrenciesFactory.makeDomainCurrencies()
        coEvery { useCase.execute() } returns domain

        // when
        cut.getCurrencies()

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs  CurrencyListViewState.CurrencyListSuccess(
            currencies = with(mapper) { domain.fromDomainToUi() }
        )
    }
}