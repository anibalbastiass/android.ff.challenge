package com.ff.challenge.feature.auth.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.feature.auth.domain.usecase.GetSignedUserUseCase
import com.ff.challenge.feature.auth.presentation.mapper.UiUserMapper
import com.ff.challenge.feature.auth.presentation.viewstate.SignInViewState
import com.ff.challenge.library.testutils.CoroutineRule
import com.ff.challenge.library.testutils.foundation.RandomFactory
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SignInViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var mapper: UiUserMapper

    @MockK
    internal lateinit var useCase: GetSignedUserUseCase

    private lateinit var cut: SignInViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = SignInViewModel(mapper, useCase)
    }

    @Test
    fun `execute SignInUseCase`() {
        // when
        val email = RandomFactory.generateRandomValidEmail()
        val password = RandomFactory.generateString()
        cut.signIn(email, password)

        // then
        coVerify { useCase.execute(any(), any()) }

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs SignInViewState.NotUserFound
    }
}