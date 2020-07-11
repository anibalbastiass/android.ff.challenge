package com.ff.challenge.feature.currencies

import com.ff.challenge.feature.currencies.ui.CurrencyListFragmentDirections
import com.ff.challenge.library.base.presentation.navigation.NavManager

class CurrenciesNavigator(private val navManager: NavManager) {

    fun navigateToSignIn() {
        navManager.navigate(
            CurrencyListFragmentDirections.actionNavigateToAuthNavGraph()
        )
    }
}