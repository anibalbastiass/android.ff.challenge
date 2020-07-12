package com.ff.challenge.feature.currencies.data.mapper

import com.ff.challenge.feature.currencies.domain.model.DomainCurrencies
import com.ff.challenge.feature.currencies.factory.CurrenciesFactory
import com.ff.challenge.library.base.presentation.extension.applyMilesSeparator
import com.ff.challenge.library.base.presentation.extension.formatDate
import junit.framework.Assert.*
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Test

class CurrenciesMapperTest {
    private val mapper = CurrenciesMapper()

    @Test
    fun `given RemoteCurrencies, when fromRemoteToDomain, then DomainCurrencies`() {
        val remote = CurrenciesFactory.makeRemoteEntities()
        val domain = with(mapper) { remote.fromRemoteToDomain() }

        assertEquals("version", remote.version, domain.version)
        assertEquals("author", remote.author, domain.author)
        assertEquals("date", remote.date?.formatDate(), domain.date)

        with(mapper) {
            assertEquals("uf", remote.uf?.fromRemoteToDomain(), domain.currencies[0])
            assertEquals(
                "valueUf",
                "${remote.uf?.value?.applyMilesSeparator()} ${getSuffix(remote.uf?.currency)}",
                domain.currencies[0].value
            )

            assertEquals("ivp", remote.ivp?.fromRemoteToDomain(), domain.currencies[1])
            assertEquals("dollar", remote.dollar?.fromRemoteToDomain(), domain.currencies[2])
            assertEquals(
                "exchangedDollar",
                remote.exchangedDollar?.fromRemoteToDomain(),
                domain.currencies[3]
            )
            assertEquals("euro", remote.euro?.fromRemoteToDomain(), domain.currencies[4])
            assertEquals("ipc", remote.ipc?.fromRemoteToDomain(), domain.currencies[5])
            assertEquals("utm", remote.utm?.fromRemoteToDomain(), domain.currencies[6])
            assertEquals("imacec", remote.imacec?.fromRemoteToDomain(), domain.currencies[7])
            assertEquals("tpm", remote.tpm?.fromRemoteToDomain(), domain.currencies[8])
            assertEquals(
                "copperPound",
                remote.copperPound?.fromRemoteToDomain(),
                domain.currencies[9]
            )
            assertEquals(
                "unEmploymentRate",
                remote.unEmploymentRate?.fromRemoteToDomain(),
                domain.currencies[10]
            )
            assertEquals("bitcoin", remote.bitcoin?.fromRemoteToDomain(), domain.currencies[11])

            domain shouldHaveTheSameClassAs DomainCurrencies.create()
        }
    }
}