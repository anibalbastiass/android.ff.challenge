package com.ff.challenge.app.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ff.challenge.AppNavigationGraphDirections
import com.ff.challenge.R
import com.ff.challenge.app.presentation.viewmodel.CacheViewModel
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.library.base.presentation.activity.BaseActivity
import com.ff.challenge.library.base.presentation.extension.observe
import com.ff.challenge.library.base.presentation.navigation.NavManager
import kotlinx.android.synthetic.main.activity_nav_host.*
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav_host
    private val navController get() = navHostFragment.findNavController()
    private val cacheViewModel: CacheViewModel by instance()
    private val navManager: NavManager by instance()

    private val stateCacheObserver = Observer<CacheViewState> { viewState ->
        when (viewState) {
            is CacheViewState.GetSessionSuccess -> {
                try {
                    navController.popBackStack(R.id.featureAuthNavGraph, true)
                    navManager.navigate(
                        AppNavigationGraphDirections.actionNavigateToCurrenciesNavGraph(
                            viewState.data.split("\n")[0],
                            viewState.data.split("\n")[1]
                        )
                    )
                } catch (e: Exception) {
                    navManager.navigate(AppNavigationGraphDirections.actionNavigateToAuthNavGraph())
                }
            }
            CacheViewState.GetSessionFailure -> {
                navManager.navigate(AppNavigationGraphDirections.actionNavigateToAuthNavGraph())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavManager()
        observe(cacheViewModel.stateLiveData, stateCacheObserver)
        cacheViewModel.getSession()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }
}
