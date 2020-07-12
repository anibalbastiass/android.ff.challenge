package com.ff.challenge.feature.currencies.presentation.model

data class UiCurrencies(
    val version: String,
    val author: String,
    val date: String,
    val currencies: List<UiCurrencyItem>
) {
    companion object {
        fun create() = UiCurrencies(
            version = "",
            author = "",
            date = "",
            currencies = arrayListOf()
        )
    }
}