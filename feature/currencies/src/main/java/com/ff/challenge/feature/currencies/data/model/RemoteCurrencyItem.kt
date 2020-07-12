package com.ff.challenge.feature.currencies.data.model

import com.ff.challenge.feature.currencies.data.remote.Constants.CODE
import com.ff.challenge.feature.currencies.data.remote.Constants.CURRENCY
import com.ff.challenge.feature.currencies.data.remote.Constants.DATE
import com.ff.challenge.feature.currencies.data.remote.Constants.NAME
import com.ff.challenge.feature.currencies.data.remote.Constants.VALUE
import com.squareup.moshi.Json

data class RemoteCurrencyItem(

    @field:Json(name = DATE)
    val date: String? = null,

    @field:Json(name = CODE)
    val code: String? = null,

    @field:Json(name = CURRENCY)
    val currency: String? = null,

    @field:Json(name = VALUE)
    val value: Double? = null,

    @field:Json(name = NAME)
    val name: String? = null
)