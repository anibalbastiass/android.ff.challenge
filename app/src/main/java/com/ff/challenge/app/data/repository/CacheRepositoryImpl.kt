package com.ff.challenge.app.data.repository

import com.ff.challenge.app.data.cache.Constants.HAS_SESSION_KEY
import com.ff.challenge.app.data.cache.FFSharedPreference
import com.ff.challenge.app.domain.repository.CacheRepository

internal class CacheRepositoryImpl(
    private val sharedPreference: FFSharedPreference
) : CacheRepository {

    override suspend fun storeSession(data: String) {
        return sharedPreference.putData(HAS_SESSION_KEY, data)
    }

    override suspend fun getSession(): String {
        return sharedPreference.getString(HAS_SESSION_KEY)
    }

    override suspend fun clearAll() {
        return sharedPreference.clearData()
    }
}
