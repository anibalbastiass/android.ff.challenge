package com.ff.challenge.app

import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.ff.challenge.BuildConfig
import com.ff.challenge.app.di.feature.FeatureManager
import com.ff.challenge.app.di.kodein.FragmentArgsExternalSource
import com.ff.challenge.appModule
import com.ff.challenge.library.base.baseModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

class FFApplication : SplitCompatApplication(), KodeinAware {

    companion object {
        lateinit var appContext: Context
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FFApplication))
        import(baseModule)
        import(appModule)
        importAll(FeatureManager.kodeinModules)

        externalSources.add(FragmentArgsExternalSource())
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
