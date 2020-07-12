package com.ff.challenge.feature.currencies.data.remote

import com.ff.challenge.feature.currencies.data.model.RemoteCurrencies
import com.ff.challenge.feature.currencies.data.remote.Constants.API_VERSION
import retrofit2.http.GET

internal interface CurrenciesRetrofitService {

    @GET(API_VERSION)
    suspend fun getCurrenciesListAsync(): RemoteCurrencies?

}
