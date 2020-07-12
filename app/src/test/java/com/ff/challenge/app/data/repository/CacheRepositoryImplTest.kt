package com.ff.challenge.app.data.repository

import com.ff.challenge.app.data.cache.FFSharedPreference
import com.ff.challenge.library.testutils.foundation.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class CacheRepositoryImplTest {

    @MockK
    internal lateinit var sharedPreference: FFSharedPreference
    private lateinit var cut: CacheRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = CacheRepositoryImpl(
            sharedPreference
        )
    }

    @Test
    fun `storeSession fetches Boolean`() {
        // given
        val data = RandomFactory.generateString()
        coEvery {
            cut.storeSession(data)
        }
    }

    @Test
    fun `getSession fetches Boolean`() {
        // given
        coEvery {
            cut.getSession()
        }
    }

    @Test
    fun `clearAllSession fetches Boolean`() {
        // given
        coEvery {
            cut.clearAll()
        }
    }
}