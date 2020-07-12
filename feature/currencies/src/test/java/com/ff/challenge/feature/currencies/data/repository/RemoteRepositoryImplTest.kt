package com.ff.challenge.feature.currencies.data.repository

import com.ff.challenge.feature.currencies.data.mapper.CurrenciesMapper
import com.ff.challenge.feature.currencies.data.remote.CurrenciesRetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class RemoteRepositoryImplTest {

    @MockK
    internal lateinit var service: CurrenciesRetrofitService

    @MockK
    internal lateinit var mapper: CurrenciesMapper

    private lateinit var cut: RemoteRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = RemoteRepositoryImpl(service, mapper)
    }

    @Test
    fun `storeSession fetches Boolean`() {
        // given
        coEvery {
            cut.getCurrenciesList()
        }
    }
}
