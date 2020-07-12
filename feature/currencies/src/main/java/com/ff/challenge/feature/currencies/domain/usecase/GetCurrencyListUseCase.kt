package com.ff.challenge.feature.currencies.domain.usecase

import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.domain.repository.RemoteRepository

open class GetCurrencyListUseCase(
    private val repository: RemoteRepository
) {
    suspend fun execute(): DomainCurrencies =
        repository.getCurrenciesList()
}
