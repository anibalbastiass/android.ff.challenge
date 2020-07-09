package com.ff.challenge.library.base.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import timber.log.Timber

abstract class BaseActivity : InjectionActivity() {

    @get:LayoutRes
    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutResId)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        Timber.v("onCreate ${javaClass.simpleName}")
    }
}
