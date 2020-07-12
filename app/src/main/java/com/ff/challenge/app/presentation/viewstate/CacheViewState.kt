package com.ff.challenge.app.presentation.viewstate

import com.ff.challenge.library.base.presentation.viewmodel.BaseViewState

sealed class CacheViewState : BaseViewState {
    object InitialState : CacheViewState()
    class GetSessionSuccess(val data: String) : CacheViewState()
    object GetSessionFailure : CacheViewState()

    object StoreSessionSuccess : CacheViewState()
    object StoreSessionFailure : CacheViewState()

    object SignOutSuccess : CacheViewState()
    object SignOutFailure : CacheViewState()
}