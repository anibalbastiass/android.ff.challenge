package com.ff.challenge.feature.currencies.presentation.model

data class UiCurrencyItem(
    val code: String,
    val name: String,
    val currency: String,
    val date: String,
    val value: String,
    var onClickAction: ((UiCurrencyItem) -> Unit)? = null
)