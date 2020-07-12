package com.ff.challenge.feature.currencies.presentation.action

import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.library.base.presentation.viewmodel.BaseAction


sealed class CurrencyListAction : BaseAction {
    class CurrencyListSuccess(val currencies: DomainCurrencies) : CurrencyListAction()
    object CurrencyListFailure : CurrencyListAction()
}