package com.ff.challenge.app.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ff.challenge.app.domain.usecase.GetSessionUseCase
import com.ff.challenge.app.domain.usecase.SignOutUseCase
import com.ff.challenge.app.domain.usecase.StoreSessionUseCase
import com.ff.challenge.app.presentation.action.CacheAction
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.library.testutils.CoroutineRule
import com.ff.challenge.library.testutils.foundation.RandomFactory.generateString
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CacheViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var getSessionUseCase: GetSessionUseCase

    @MockK
    internal lateinit var storeSessionUseCase: StoreSessionUseCase

    @MockK
    internal lateinit var signOutUseCase: SignOutUseCase

    private lateinit var cut: CacheViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = CacheViewModel(
            getSessionUseCase,
            storeSessionUseCase,
            signOutUseCase
        )
    }

    @Test
    fun `execute GetSessionUseCase`() {
        // when
        val data = generateString()
        cut.getSession()

        // then
        coVerify { getSessionUseCase.execute() }
        cut.sendAction(CacheAction.GetSessionSuccess(data))

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs CacheViewState.GetSessionSuccess(data)
    }

    @Test
    fun `verify state when StoreSessionUseCase`() {
        // given
        val data = generateString()
        coEvery { storeSessionUseCase.execute(data) }

        // when
        cut.storeSession(data)
        cut.sendAction(CacheAction.StoreSessionSuccess)

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs CacheViewState.StoreSessionSuccess
    }

    @Test
    fun `verify state when SignOutSessionUseCase`() {
        // given
        coEvery { signOutUseCase.execute() }

        // when
        cut.signOut()
        cut.sendAction(CacheAction.SignOutSuccess)

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs CacheViewState.SignOutSuccess
    }

    @Test
    fun `verify state when SignOutSessionUseCase fails`() {
        // given
        coEvery { signOutUseCase.execute() }

        // when
        cut.signOut()
        cut.sendAction(CacheAction.SignOutFailure)

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs CacheViewState.SignOutFailure
    }
}