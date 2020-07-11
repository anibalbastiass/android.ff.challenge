package com.ff.challenge.feature.currencies.ui

import com.ff.challenge.feature.currencies.CurrenciesNavigator
import com.ff.challenge.feature.currencies.MODULE_NAME
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val uiModule = Kodein.Module("${MODULE_NAME}UiModule") {

    bind() from singleton { CurrenciesNavigator(instance()) }

}
