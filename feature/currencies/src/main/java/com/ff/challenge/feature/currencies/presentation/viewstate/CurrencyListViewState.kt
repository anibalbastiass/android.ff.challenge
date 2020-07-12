package com.ff.challenge.feature.currencies.presentation.viewstate

import com.ff.challenge.feature.currencies.presentation.model.UiCurrencies
import com.ff.challenge.library.base.presentation.viewmodel.BaseViewState


sealed class CurrencyListViewState : BaseViewState {
    object InitialState : CurrencyListViewState()
    class CurrencyListSuccess(val currencies: UiCurrencies) : CurrencyListViewState()
    object CurrencyListFailure : CurrencyListViewState()
}