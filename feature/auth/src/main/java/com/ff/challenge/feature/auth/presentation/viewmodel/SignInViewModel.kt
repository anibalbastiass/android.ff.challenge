package com.ff.challenge.feature.auth.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.ff.challenge.BuildConfig
import com.ff.challenge.feature.auth.R
import com.ff.challenge.feature.auth.domain.usecase.GetSignedUserUseCase
import com.ff.challenge.feature.auth.presentation.action.SignInAction
import com.ff.challenge.feature.auth.presentation.mapper.UiUserMapper
import com.ff.challenge.feature.auth.presentation.viewstate.SignInViewState
import com.ff.challenge.library.base.presentation.extension.EncryptExtension
import com.ff.challenge.library.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class SignInViewModel(
    private val mapper: UiUserMapper,
    private val useCase: GetSignedUserUseCase
) : BaseViewModel<SignInViewState, SignInAction>(
    SignInViewState.InitialState
) {

    override fun onReduceState(viewAction: SignInAction) = when (viewAction) {
        is SignInAction.SignInSuccess   -> SignInViewState.UserFound(
            user = with(mapper) { viewAction.user.fromDomainToUi() }
        )
        is SignInAction.UserNotFound    -> SignInViewState.NotUserFound
        is SignInAction.InvalidEmail    -> SignInViewState.InvalidEmail(viewAction.error)
        is SignInAction.InvalidPassword -> SignInViewState.InvalidPassword(viewAction.error)
        SignInAction.ValidDataUser      -> SignInViewState.ValidDataUser
    }

    fun signIn(email: String, password: String) {
        val encryptedPassword =
            with(EncryptExtension) {
                encrypt(
                    password,
                    BuildConfig.GRADLE_SECRET_KEY,
                    BuildConfig.GRADLE_SALT_KEY
                )
            }

        viewModelScope.launch {
            try {
                useCase.execute(email, encryptedPassword)?.also {
                    sendAction(
                        SignInAction.SignInSuccess(
                            it
                        )
                    )
                }
            } catch (e: Exception) {
                postErrorAction()
            }
        }
    }

    private fun postErrorAction() {
        sendAction(SignInAction.UserNotFound)
    }

    fun signInDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            sendAction(SignInAction.InvalidEmail(error = R.string.invalid_username))
        } else if (!isPasswordValid(password)) {
            sendAction(SignInAction.InvalidPassword(error = R.string.invalid_password))
        } else {
            sendAction(SignInAction.ValidDataUser)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
