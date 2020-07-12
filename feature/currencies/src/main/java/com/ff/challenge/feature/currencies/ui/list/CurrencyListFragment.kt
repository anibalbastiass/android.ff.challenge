package com.ff.challenge.feature.currencies.ui.list

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ff.challenge.app.presentation.viewmodel.CacheViewModel
import com.ff.challenge.app.presentation.viewstate.CacheViewState
import com.ff.challenge.feature.currencies.CurrenciesNavigator
import com.ff.challenge.feature.currencies.R
import com.ff.challenge.feature.currencies.databinding.FragmentCurrencyListBinding
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencies
import com.ff.challenge.feature.currencies.presentation.model.UiCurrencyItem
import com.ff.challenge.feature.currencies.presentation.viewmodel.CurrencyListViewModel
import com.ff.challenge.feature.currencies.presentation.viewstate.CurrencyListViewState
import com.ff.challenge.feature.currencies.ui.list.adapter.CurrencyListAdapter
import com.ff.challenge.library.base.presentation.extension.RecyclerViewExtension
import com.ff.challenge.library.base.presentation.extension.ToolbarExtension
import com.ff.challenge.library.base.presentation.extension.isNetworkAvailable
import com.ff.challenge.library.base.presentation.extension.observe
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.toast
import com.pawegio.kandroid.visible
import org.kodein.di.generic.instance

class CurrencyListFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_currency_list
    private lateinit var binding: FragmentCurrencyListBinding
    private val cacheViewModel: CacheViewModel by instance()
    private val currenciesViewModel: CurrencyListViewModel by instance()
    private val args: CurrencyListFragmentArgs by navArgs()
    private val navigator: CurrenciesNavigator by instance()
    private val currencyAdapter: CurrencyListAdapter by instance()
    private val connectionManager: ConnectivityManager by instance()

    private val stateCacheObserver = Observer<CacheViewState> { viewState ->
        when (viewState) {
            CacheViewState.SignOutSuccess -> {
                navigator.navigateToSignIn()
            }
        }
    }

    private val stateObserver = Observer<CurrencyListViewState> { viewState ->
        when (viewState) {
            is CurrencyListViewState.CurrencyListSuccess -> {
                setCurrencyData(viewState.currencies)
            }
            CurrencyListViewState.CurrencyListFailure -> {
                binding.progressBar.visible = false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentCurrencyListBinding
        binding.lifecycleOwner = this

        observe(cacheViewModel.stateLiveData, stateCacheObserver)
        observe(currenciesViewModel.stateLiveData, stateObserver)

        binding.btnSignOut.setOnClickListener {
            cacheViewModel.signOut()
        }
        setToolbar()
        binding.rvCurrencies.setCurrenciesAdapter()

        (currenciesViewModel.stateLiveData.value as? CurrencyListViewState.CurrencyListSuccess)
            ?.currencies?.let {
                binding.progressBar.visible = false
                setCurrencyData(it)
            } ?: run {
            requestSecureData()
        }
    }

    private fun setCurrencyData(currencies: UiCurrencies) {
        binding.progressBar.visible = false
        binding.rvCurrencies.visible = true

        with(currencies) {
            binding.footer = "$author ($version) - $date"
        }

        currencyAdapter.onClickAction = object : CurrencyListAdapter.ClickListener {
            override fun onClickAction(item: UiCurrencyItem) {
                navigator.navigateToDetails(item)
            }
        }

        currencyAdapter.items = currencies.currencies as MutableList<UiCurrencyItem>
    }

    private fun RecyclerView.setCurrenciesAdapter() {
        layoutManager = GridLayoutManager(context, 2)
        adapter = currencyAdapter
    }

    private fun requestSecureData() {
        if (connectionManager.isNetworkAvailable()) {
            currenciesViewModel.loadData()
        } else {
            activity?.toast(R.string.error_connection_internet)
        }
    }

    private fun setToolbar() {
        binding.tbCurrencies.apply {
            with(ToolbarExtension) {
                applyFontForToolbarTitle(requireActivity())
                setNoArrowUpToolbar(requireActivity())
            }
            title = getString(R.string.hello_user, args.fullName)
        }
    }
}
