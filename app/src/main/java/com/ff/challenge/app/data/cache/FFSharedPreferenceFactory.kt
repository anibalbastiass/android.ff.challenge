package com.ff.challenge.app.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.ff.challenge.app.data.cache.Constants.MODE
import com.ff.challenge.app.data.cache.Constants.NAME

object FFSharedPreferenceFactory {

    fun makeSharedPreferenceForFF(context: Context): SharedPreferences {
        return context.getSharedPreferences(NAME, MODE)
    }
}
