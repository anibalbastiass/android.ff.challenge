package com.ff.challenge.feature.auth

import com.ff.challenge.feature.auth.ui.signin.SignInFragmentDirections
import com.ff.challenge.library.base.presentation.navigation.NavManager

class AuthNavigator(private val navManager: NavManager) {

    fun navigateToCurrencies(email: String, fullName: String) {
        navManager.navigate(
            SignInFragmentDirections.actionSignInFragmentToFeatureCurrenciesNavGraph(
                email,
                fullName
            )
        )
    }
}