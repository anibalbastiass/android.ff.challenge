package com.ff.challenge.feature.currencies.data.repository

import com.ff.challenge.feature.currencies.data.mapper.CurrenciesMapper
import com.ff.challenge.feature.currencies.data.remote.CurrenciesRetrofitService
import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.domain.repository.RemoteRepository


internal class RemoteRepositoryImpl(
    private val service: CurrenciesRetrofitService,
    private val mapper: CurrenciesMapper
) : RemoteRepository {

    override suspend fun getCurrenciesList(): DomainCurrencies =
        with(mapper) {
            service.getCurrenciesListAsync()?.fromRemoteToDomain()
        } ?: DomainCurrencies.create()
}
