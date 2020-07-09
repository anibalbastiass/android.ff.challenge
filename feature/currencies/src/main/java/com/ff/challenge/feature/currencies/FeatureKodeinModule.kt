package com.ff.challenge.feature.currencies

import com.ff.challenge.app.feature.KodeinModuleProvider
import com.ff.challenge.feature.currencies.data.dataModule
import com.ff.challenge.feature.currencies.domain.domainModule
import com.ff.challenge.feature.currencies.presentation.presentationModule
import com.ff.challenge.feature.currencies.ui.uiModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Currencies"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(uiModule)
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
