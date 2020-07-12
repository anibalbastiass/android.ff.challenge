package com.ff.challenge.feature.currencies.domain.model

data class DomainCurrencies(
    val version: String,
    val author: String,
    val date: String,
    val currencies: List<DomainCurrencyItem>
) {
    companion object {
        fun create() = DomainCurrencies(
            version = "",
            author = "",
            date = "",
            currencies = arrayListOf()
        )
    }
}