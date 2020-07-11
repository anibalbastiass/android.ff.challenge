package com.ff.challenge.feature.auth.domain

import com.ff.challenge.feature.auth.MODULE_NAME
import com.ff.challenge.feature.auth.domain.local.LocalRepositoryImpl
import com.ff.challenge.feature.auth.domain.mapper.LocalUserMapper
import com.ff.challenge.feature.auth.domain.repository.LocalRepository
import com.ff.challenge.feature.auth.domain.usecase.GetSignedUserUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind<LocalRepository>() with singleton {
        LocalRepositoryImpl(
            instance(),
            instance()
        )
    }

    bind() from singleton { LocalUserMapper() }

    bind() from singleton { GetSignedUserUseCase(instance()) }
}
