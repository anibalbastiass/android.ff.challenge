package com.ff.challenge.feature.auth.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.feature.auth.domain.usecase.GetSignedUserUseCase
import com.ff.challenge.feature.auth.factory.UserFactory
import com.ff.challenge.feature.auth.presentation.action.SignInAction
import com.ff.challenge.feature.auth.presentation.mapper.UiUserMapper
import com.ff.challenge.feature.auth.presentation.viewstate.SignInViewState
import com.ff.challenge.library.testutils.CoroutineRule
import com.ff.challenge.library.testutils.foundation.RandomFactory
import com.ff.challenge.library.testutils.foundation.RandomFactory.generateInt
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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

    lateinit var mapper: UiUserMapper

    @MockK
    internal lateinit var useCase: GetSignedUserUseCase

    private lateinit var cut: SignInViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = UiUserMapper()
        cut = SignInViewModel(mapper, useCase)
    }

    @Test
    fun `execute SignInUseCase return null`() {
        // when
        val email = RandomFactory.generateRandomValidEmail()
        val password = RandomFactory.generateString()
        cut.signIn(email, password)

        // then
        coVerify { useCase.execute(any(), any()) }

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs SignInViewState.NotUserFound
    }

    @Test
    fun `execute SignInUseCase`() {
        // when
        val domain = UserFactory.makeDomainUser()
        val email = RandomFactory.generateRandomValidEmail()
        val password = RandomFactory.generateString()
        cut.signIn(email, password)

        // then
        coEvery { useCase.execute(any(), any()) } returns domain
        cut.sendAction(SignInAction.SignInSuccess(domain))

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs SignInViewState.UserFound(
            with(mapper) { domain.fromDomainToUi() }
        )
    }

    @Test
    fun `execute ValidUser`() {
        // then
        cut.sendAction(SignInAction.ValidDataUser)

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs SignInViewState.ValidDataUser
    }

    @Test
    fun `execute InvalidEmail`() {
        // when
        val error = generateInt()

        // when
        cut.sendAction(SignInAction.InvalidEmail(error))

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs SignInViewState.InvalidEmail(error)
    }

    @Test
    fun `execute InvalidPassword`() {
        // when
        val error = generateInt()

        // when
        cut.sendAction(SignInAction.InvalidPassword(error))

        // then
        cut.stateLiveData.value shouldHaveTheSameClassAs SignInViewState.InvalidPassword(error)
    }
}