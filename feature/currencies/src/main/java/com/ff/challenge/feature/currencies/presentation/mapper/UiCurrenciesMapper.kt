package com.ff.challenge.feature.currencies.presentation.mapper

import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencyItem
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencies
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencyItem

class UiCurrenciesMapper {

    fun DomainCurrencies.fromDomainToUi() = UiCurrencies(
        version = version,
        author = author,
        date = date,
        currencies = currencies.map { it.fromDomainToUi() }
    )

    private fun DomainCurrencyItem.fromDomainToUi() = UiCurrencyItem(
        code = code,
        name = name,
        currency = currency,
        date = date,
        value = value
    )

}