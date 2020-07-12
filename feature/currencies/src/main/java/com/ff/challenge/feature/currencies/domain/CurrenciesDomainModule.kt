package com.ff.challenge.feature.currencies.domain

import com.ff.challenge.feature.currencies.MODULE_NAME
import com.ff.challenge.feature.currencies.domain.usecase.GetCurrencyListUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetCurrencyListUseCase(instance()) }

}
