package com.ff.challenge.app.data.cache

import android.content.SharedPreferences

class FFSharedPreference constructor(private val sharedPreference: SharedPreferences) {

    fun putData(key: String, data: String) {
        sharedPreference.edit().putString(key, data).apply()
    }

    fun putData(key: String, data: Boolean) {
        sharedPreference.edit().putBoolean(key, data).apply()
    }

    fun getString(key: String): String {
        return sharedPreference.getString(key, "").toString()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun clearData() {
        sharedPreference.edit().clear().apply()
    }
}
