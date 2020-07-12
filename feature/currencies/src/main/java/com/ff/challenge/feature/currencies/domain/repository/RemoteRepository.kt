package com.ff.challenge.feature.currencies.domain.repository

import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies


interface RemoteRepository {

    suspend fun getCurrenciesList(): DomainCurrencies

}
