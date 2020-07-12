package com.ff.challenge.feature.currencies

import com.ff.challenge.feature.currencies.presentation.model.UiCurrencyItem
import com.ff.challenge.feature.currencies.ui.list.CurrencyListFragmentDirections
import com.ff.challenge.library.base.presentation.navigation.NavManager

class CurrenciesNavigator(private val navManager: NavManager) {

    fun navigateToSignIn() {
        navManager.navigate(
            CurrencyListFragmentDirections.actionNavigateToAuthNavGraph()
        )
    }

    fun navigateToDetails(currency: UiCurrencyItem) {

    }
}