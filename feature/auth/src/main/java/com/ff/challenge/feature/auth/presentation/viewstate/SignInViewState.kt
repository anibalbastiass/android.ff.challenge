package com.ff.challenge.feature.auth.presentation.viewstate

import com.ff.challenge.feature.auth.presentation.model.UiUser
import com.ff.challenge.library.base.presentation.viewmodel.BaseViewState

sealed class SignInViewState : BaseViewState {
    object InitialState : SignInViewState()
    class UserFound(val user: UiUser) : SignInViewState()
    class InvalidEmail(val error: Int) : SignInViewState()
    class InvalidPassword(val error: Int) : SignInViewState()
    object ValidDataUser : SignInViewState()
    object NotUserFound : SignInViewState()
}
