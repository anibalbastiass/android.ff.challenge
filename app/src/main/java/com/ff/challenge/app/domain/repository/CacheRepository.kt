package com.ff.challenge.app.domain.repository

interface CacheRepository {

    suspend fun storeSession(data: String)

    suspend fun getSession(): String

    suspend fun clearAll()
}
