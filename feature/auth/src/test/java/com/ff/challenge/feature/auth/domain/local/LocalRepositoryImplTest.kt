package com.ff.challenge.feature.auth.domain.local

import com.ff.challenge.feature.auth.data.local.AuthDatabase
import com.ff.challenge.feature.auth.domain.mapper.LocalUserMapper
import com.ff.challenge.library.testutils.foundation.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class LocalRepositoryImplTest {

    @MockK
    internal lateinit var mapper: LocalUserMapper

    @MockK
    internal lateinit var database: AuthDatabase

    private lateinit var cut: LocalRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = LocalRepositoryImpl(
            mapper,
            database
        )
    }

    @Test
    fun `getUserByEmailPassword fetches data`() {
        // given
        val email = RandomFactory.generateRandomValidEmail()
        val password = RandomFactory.generateString()
        coEvery {
            cut.getUserByEmailPassword(email, password)
        }
    }
}
