package com.ff.challenge.feature.currencies.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ff.challenge.app.presentation.viewmodel.CacheViewModel
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.feature.currencies.CurrenciesNavigator
import com.ff.challenge.feature.currencies.R
import com.ff.challenge.feature.currencies.databinding.FragmentCurrencyListBinding
import com.ff.challenge.library.base.presentation.extension.observe
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.toast
import org.kodein.di.generic.instance

class CurrencyListFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_currency_list
    private lateinit var binding: FragmentCurrencyListBinding
    private val cacheViewModel: CacheViewModel by instance()
    private val args: CurrencyListFragmentArgs by navArgs()
    private val navigator: CurrenciesNavigator by instance()

    private val stateCacheObserver = Observer<CacheViewState> { viewState ->
        when (viewState) {
            CacheViewState.SignOutSuccess -> {
                navigator.navigateToSignIn()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentCurrencyListBinding
        binding.lifecycleOwner = this

        binding.tvTitle.text = args.email

        observe(cacheViewModel.stateLiveData, stateCacheObserver)

        binding.btnSignOut.setOnClickListener {
            cacheViewModel.signOut()
        }
    }

}