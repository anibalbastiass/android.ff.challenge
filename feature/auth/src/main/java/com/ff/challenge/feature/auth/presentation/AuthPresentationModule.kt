package com.ff.challenge.feature.auth.presentation

import androidx.fragment.app.Fragment
import com.ff.challenge.app.presentation.viewmodel.CacheViewModel
import com.ff.challenge.feature.auth.MODULE_NAME
import com.ff.challenge.feature.auth.presentation.mapper.UiUserMapper
import com.ff.challenge.feature.auth.presentation.viewmodel.SignInViewModel
import com.ff.challenge.library.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<SignInViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            SignInViewModel(
                instance(),
                instance()
            )
        }
    }

    bind<CacheViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            CacheViewModel(
                instance(),
                instance(),
                instance()
            )
        }
    }

    bind() from singleton { UiUserMapper() }
}
