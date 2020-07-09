package com.ff.challenge.feature.auth

import com.ff.challenge.app.feature.KodeinModuleProvider
import com.ff.challenge.feature.auth.data.dataModule
import com.ff.challenge.feature.auth.domain.domainModule
import com.ff.challenge.feature.auth.presentation.presentationModule
import com.ff.challenge.feature.auth.ui.uiModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Auth"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(uiModule)
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
