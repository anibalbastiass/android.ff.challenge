package com.ff.challenge.app.presentation.action

import com.ff.challenge.library.base.presentation.viewmodel.BaseAction

sealed class CacheAction : BaseAction {
    class GetSessionSuccess(val data: String) : CacheAction()
    object GetSessionFailure : CacheAction()

    object StoreSessionSuccess : CacheAction()
    object StoreSessionFailure : CacheAction()

    object SignOutSuccess : CacheAction()
    object SignOutFailure : CacheAction()
}