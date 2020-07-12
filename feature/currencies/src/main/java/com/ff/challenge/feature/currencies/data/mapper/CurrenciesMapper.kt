package com.ff.challenge.feature.currencies.data.mapper

import com.ff.challenge.feature.currencies.data.model.RemoteCurrencies
import com.ff.challenge.feature.currencies.data.model.RemoteCurrencyItem
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencyItem
import com.ff.challenge.library.base.presentation.extension.applyMilesSeparator
import com.ff.challenge.library.base.presentation.extension.formatDate

class CurrenciesMapper {

    companion object {
        const val PERCENTAGE = "Porcentaje"
        const val PERCENTAGE_SUFFIX = "%"
        const val DOLLAR = "Dólar"
        const val DOLLAR_SUFFIX = "USD"
        const val PESOS = "Pesos"
        const val PESOS_SUFIX = "CLP"
    }


    fun RemoteCurrencies.fromRemoteToDomain(): DomainCurrencies {

        val currencies = arrayListOf<DomainCurrencyItem>()
        uf?.fromRemoteToDomain()?.let { currencies.add(it) }
        ivp?.fromRemoteToDomain()?.let { currencies.add(it) }
        dollar?.fromRemoteToDomain()?.let { currencies.add(it) }
        exchangedDollar?.fromRemoteToDomain()?.let { currencies.add(it) }
        euro?.fromRemoteToDomain()?.let { currencies.add(it) }
        ipc?.fromRemoteToDomain()?.let { currencies.add(it) }
        utm?.fromRemoteToDomain()?.let { currencies.add(it) }
        imacec?.fromRemoteToDomain()?.let { currencies.add(it) }
        tpm?.fromRemoteToDomain()?.let { currencies.add(it) }
        copperPound?.fromRemoteToDomain()?.let { currencies.add(it) }
        unEmploymentRate?.fromRemoteToDomain()?.let { currencies.add(it) }
        bitcoin?.fromRemoteToDomain()?.let { currencies.add(it) }

        return DomainCurrencies(
            version = version ?: "",
            author = author ?: "",
            date = date?.formatDate() ?: "",
            currencies = currencies
        )
    }

    private fun RemoteCurrencyItem.fromRemoteToDomain() = DomainCurrencyItem(
        code = code ?: "",
        name = name ?: "",
        currency = currency ?: "",
        date = date?.formatDate() ?: "",
        value = "${value?.applyMilesSeparator()} ${getSuffix(currency)}"
    )

    private fun getSuffix(currency: String?) = when (currency) {
        PERCENTAGE -> PERCENTAGE_SUFFIX
        DOLLAR -> DOLLAR_SUFFIX
        PESOS -> PESOS_SUFIX
        else -> ""
    }
}