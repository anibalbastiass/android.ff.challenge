package com.ff.challenge.feature.currencies.presentation.mapper

import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencyItem
import com.ff.challenge.feature.currencies.factory.CurrenciesFactory
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencies
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencyItem
import junit.framework.Assert.assertEquals
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Test

class UiCurrenciesMapperTest {
    private val mapper = UiCurrenciesMapper()

    @Test
    fun `given RemoteCurrencies, when fromRemoteToDomain, then DomainCurrencies`() {
        val domain = CurrenciesFactory.makeDomainCurrencies()
        val ui = with(mapper) { domain.fromDomainToUi() }

        assertEquals("version", domain.version, ui.version)
        assertEquals("author", domain.author, ui.author)
        assertEquals("date", domain.date, ui.date)

        assertEqualsCurrencyItem(domain.currencies, ui.currencies)

        ui shouldHaveTheSameClassAs UiCurrencies.create()
    }

    private fun assertEqualsCurrencyItem(
        domain: List<DomainCurrencyItem>,
        ui: List<UiCurrencyItem>
    ) {
        domain.zip(ui).map {
            val domainItem = it.first
            val uiItem = it.second

            assertEquals("code", domainItem.code, uiItem.code)
            assertEquals("name", domainItem.name, uiItem.name)
            assertEquals("currency", domainItem.currency, uiItem.currency)
            assertEquals("value", domainItem.value, uiItem.value)
            assertEquals("date", domainItem.date, uiItem.date)

            uiItem shouldHaveTheSameClassAs UiCurrencyItem.create()
        }
    }
}