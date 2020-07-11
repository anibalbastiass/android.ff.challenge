package com.ff.challenge.feature.auth.presentation.action

import com.ff.challenge.feature.auth.domain.model.DomainUser
import com.ff.challenge.library.base.presentation.viewmodel.BaseAction

sealed class SignInAction : BaseAction {
    class SignInSuccess(val user: DomainUser) : SignInAction()
    class InvalidEmail(val error: Int) : SignInAction()
    class InvalidPassword(val error: Int) : SignInAction()
    object ValidDataUser : SignInAction()
    object UserNotFound : SignInAction()
}