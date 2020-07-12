package com.ff.challenge.feature.currencies.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ff.challenge.feature.currencies.domain.usecase.GetCurrencyListUseCase
import com.ff.challenge.feature.currencies.presentation.action.CurrencyListAction
import com.ff.challenge.feature.currencies.presentation.mapper.UiCurrenciesMapper
import com.ff.challenge.feature.currencies.presentation.viewstate.CurrencyListViewState
import com.ff.challenge.library.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class CurrencyListViewModel(
    private val mapper: UiCurrenciesMapper,
    private val useCase: GetCurrencyListUseCase
) : BaseViewModel<CurrencyListViewState, CurrencyListAction>(
    CurrencyListViewState.InitialState
) {

    override fun onLoadData() {
        getCurrencies()
    }

    override fun onReduceState(viewAction: CurrencyListAction) = when (viewAction) {
        is CurrencyListAction.CurrencyListSuccess -> CurrencyListViewState.CurrencyListSuccess(
            currencies = with(mapper) { viewAction.currencies.fromDomainToUi() }
        )
        is CurrencyListAction.CurrencyListFailure -> CurrencyListViewState.CurrencyListFailure
    }

    private fun getCurrencies() {
        viewModelScope.launch {
            try {
                useCase.execute().also {
                    sendAction(
                        CurrencyListAction.CurrencyListSuccess(
                            it
                        )
                    )
                }
            } catch (e: Exception) {
                postErrorAction()
            }
        }
    }

    private fun postErrorAction() {
        sendAction(CurrencyListAction.CurrencyListFailure)
    }
}
