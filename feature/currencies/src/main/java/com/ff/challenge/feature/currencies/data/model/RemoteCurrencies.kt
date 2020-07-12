package com.ff.challenge.feature.currencies.data.model

import com.ff.challenge.feature.currencies.data.remote.Constants.AUTHOR
import com.ff.challenge.feature.currencies.data.remote.Constants.BITCOIN
import com.ff.challenge.feature.currencies.data.remote.Constants.COPPER_POUND
import com.ff.challenge.feature.currencies.data.remote.Constants.DATE
import com.ff.challenge.feature.currencies.data.remote.Constants.DOLLAR
import com.ff.challenge.feature.currencies.data.remote.Constants.EURO
import com.ff.challenge.feature.currencies.data.remote.Constants.EXCHANGED_DOLLAR
import com.ff.challenge.feature.currencies.data.remote.Constants.IMACEC
import com.ff.challenge.feature.currencies.data.remote.Constants.IPC
import com.ff.challenge.feature.currencies.data.remote.Constants.IVP
import com.ff.challenge.feature.currencies.data.remote.Constants.TPM
import com.ff.challenge.feature.currencies.data.remote.Constants.UF
import com.ff.challenge.feature.currencies.data.remote.Constants.UN_EMPLOYMENT_RATE
import com.ff.challenge.feature.currencies.data.remote.Constants.UTM
import com.ff.challenge.feature.currencies.data.remote.Constants.VERSION
import com.squareup.moshi.Json

data class RemoteCurrencies(

    @field:Json(name = AUTHOR)
    val author: String? = null,

    @field:Json(name = VERSION)
    val version: String? = null,

    @field:Json(name = DATE)
    val date: String? = null,

    @field:Json(name = IVP)
    val ivp: RemoteCurrencyItem? = null,

    @field:Json(name = EURO)
    val euro: RemoteCurrencyItem? = null,

    @field:Json(name = EXCHANGED_DOLLAR)
    val exchangedDollar: RemoteCurrencyItem? = null,

    @field:Json(name = DOLLAR)
    val dollar: RemoteCurrencyItem? = null,

    @field:Json(name = COPPER_POUND)
    val copperPound: RemoteCurrencyItem? = null,

    @field:Json(name = UTM)
    val utm: RemoteCurrencyItem? = null,

    @field:Json(name = TPM)
    val tpm: RemoteCurrencyItem? = null,

    @field:Json(name = UF)
    val uf: RemoteCurrencyItem? = null,

    @field:Json(name = UN_EMPLOYMENT_RATE)
    val unEmploymentRate: RemoteCurrencyItem? = null,

    @field:Json(name = IPC)
    val ipc: RemoteCurrencyItem? = null,

    @field:Json(name = IMACEC)
    val imacec: RemoteCurrencyItem? = null,

    @field:Json(name = BITCOIN)
    val bitcoin: RemoteCurrencyItem? = null
)