package com.ff.challenge.feature.currencies.data

import com.ff.challenge.feature.currencies.MODULE_NAME
import com.ff.challenge.feature.currencies.data.mapper.CurrenciesMapper
import com.ff.challenge.feature.currencies.data.remote.CurrenciesRetrofitService
import com.ff.challenge.feature.currencies.data.repository.RemoteRepositoryImpl
import com.ff.challenge.feature.currencies.domain.repository.RemoteRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind() from singleton { instance<Retrofit>().create(CurrenciesRetrofitService::class.java) }

    bind<RemoteRepository>() with singleton {
        RemoteRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind() from singleton { CurrenciesMapper() }
}
