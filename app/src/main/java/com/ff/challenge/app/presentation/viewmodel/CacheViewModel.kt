package com.ff.challenge.app.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.ff.challenge.app.domain.usecase.GetSessionUseCase
import com.ff.challenge.app.domain.usecase.SignOutUseCase
import com.ff.challenge.app.domain.usecase.StoreSessionUseCase
import com.ff.challenge.app.presentation.action.CacheAction
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.library.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

open class CacheViewModel(
    private val getSessionUseCase: GetSessionUseCase,
    private val storeSessionUseCase: StoreSessionUseCase,
    private val signOutUseCase: SignOutUseCase
) : BaseViewModel<CacheViewState, CacheAction>(CacheViewState.InitialState) {

    override fun onReduceState(viewAction: CacheAction): CacheViewState =
        when (viewAction) {
            is CacheAction.GetSessionSuccess    -> CacheViewState.GetSessionSuccess(viewAction.data)
            CacheAction.GetSessionFailure       -> CacheViewState.GetSessionFailure
            CacheAction.StoreSessionSuccess     -> CacheViewState.StoreSessionSuccess
            CacheAction.StoreSessionFailure     -> CacheViewState.StoreSessionFailure
            CacheAction.SignOutSuccess          -> CacheViewState.SignOutSuccess
            CacheAction.SignOutFailure          -> CacheViewState.SignOutFailure
        }

    fun getSession() {
        viewModelScope.launch {
            getSessionUseCase.execute().also {
                try {
                    sendAction(CacheAction.GetSessionSuccess(it))
                } catch (e: Exception) {
                    sendAction(CacheAction.GetSessionFailure)
                }
            }
        }
    }

    fun storeSession(data: String) {
        viewModelScope.launch {
            storeSessionUseCase.execute(data).also {
                try {
                    sendAction(CacheAction.StoreSessionSuccess)
                } catch (e: Exception) {
                    sendAction(CacheAction.StoreSessionFailure)
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.execute().also {
                try {
                    sendAction(CacheAction.SignOutSuccess)
                } catch (e: Exception) {
                    sendAction(CacheAction.SignOutFailure)
                }
            }
        }
    }
}
