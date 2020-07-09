package com.ff.challenge.library.base.presentation.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ff.challenge.library.base.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.kodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.kcontext

abstract class InjectionActivity : AppCompatActivity(), KodeinAware {

    private val parentKodein by kodein()

    @SuppressWarnings("LeakingThisInConstructor")
    final override val kodeinContext = kcontext<AppCompatActivity>(this)

    final override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }

    final override val kodeinTrigger: KodeinTrigger? by lazy {
        if (BuildConfig.DEBUG) KodeinTrigger() else super.kodeinTrigger
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        kodeinTrigger?.trigger()
    }
}
