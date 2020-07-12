package com.ff.challenge.feature.currencies.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiCurrencyItem(
    val code: String,
    val name: String,
    val currency: String,
    val date: String,
    val value: String
) : Parcelable {

    companion object {
        fun create() = UiCurrencyItem(
            code = "",
            name = "",
            currency = "",
            date = "",
            value = ""
        )
    }
}