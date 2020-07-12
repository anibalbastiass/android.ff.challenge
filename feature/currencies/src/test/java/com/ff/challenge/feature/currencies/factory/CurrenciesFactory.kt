package com.ff.challenge.feature.currencies.factory

import com.ff.challenge.feature.currencies.data.model.RemoteCurrencies
import com.ff.challenge.feature.currencies.data.model.RemoteCurrencyItem
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencyItem
import com.ff.challenge.library.testutils.foundation.RandomFactory.generateDate
import com.ff.challenge.library.testutils.foundation.RandomFactory.generateDouble
import com.ff.challenge.library.testutils.foundation.RandomFactory.generateString

object CurrenciesFactory {

    fun makeRemoteEntities() = RemoteCurrencies(
        author = generateString(),
        version = generateString(),
        date = generateDate(),
        ivp = makeRemoteCurrencyItem(),
        euro = makeRemoteCurrencyItem(),
        exchangedDollar = makeRemoteCurrencyItem(),
        dollar = makeRemoteCurrencyItem(),
        copperPound = makeRemoteCurrencyItem(),
        utm = makeRemoteCurrencyItem(),
        tpm = makeRemoteCurrencyItem(),
        uf = makeRemoteCurrencyItem(),
        unEmploymentRate = makeRemoteCurrencyItem(),
        ipc = makeRemoteCurrencyItem(),
        imacec = makeRemoteCurrencyItem(),
        bitcoin = makeRemoteCurrencyItem()
    )

    private fun makeRemoteCurrencyItem() = RemoteCurrencyItem(
        code = generateString(),
        name = generateString(),
        currency = generateString(),
        date = generateDate(),
        value = generateDouble()
    )

    fun makeDomainCurrencies() = DomainCurrencies(
        author = generateString(),
        version = generateString(),
        date = generateDate(),
        currencies = (0..5).map {
            makeDomainCurrencyItem()
        }
    )

    private fun makeDomainCurrencyItem() = DomainCurrencyItem(
        code = generateString(),
        name = generateString(),
        currency = generateString(),
        date = generateDate(),
        value = generateString()
    )
}