package com.ff.challenge.feature.currencies.presentation

import androidx.fragment.app.Fragment
import com.ff.challenge.feature.currencies.MODULE_NAME
import com.ff.challenge.feature.currencies.presentation.mapper.UiCurrenciesMapper
import com.ff.challenge.feature.currencies.presentation.viewmodel.CurrencyListViewModel
import com.ff.challenge.library.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<CurrencyListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            CurrencyListViewModel(
                instance(),
                instance()
            )
        }
    }

    bind() from singleton { UiCurrenciesMapper() }

}
