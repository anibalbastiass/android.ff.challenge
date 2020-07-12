package com.ff.challenge.feature.currencies.ui.list

import android.database.Cursor
import android.database.MatrixCursor
import android.net.ConnectivityManager
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
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
import com.ff.challenge.library.base.presentation.extension.StringExtension
import com.ff.challenge.library.base.presentation.extension.ToolbarExtension
import com.ff.challenge.library.base.presentation.extension.isNetworkAvailable
import com.ff.challenge.library.base.presentation.extension.observe
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.visible
import org.kodein.di.generic.instance
import java.util.*

class CurrencyListFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_currency_list
    private lateinit var binding: FragmentCurrencyListBinding
    private val cacheViewModel: CacheViewModel by instance()
    private val currenciesViewModel: CurrencyListViewModel by instance()
    private val args: CurrencyListFragmentArgs by navArgs()
    private val navigator: CurrenciesNavigator by instance()
    private val currencyAdapter: CurrencyListAdapter by instance()
    private val connectionManager: ConnectivityManager by instance()

    private val suggestionsList = arrayListOf<String>()
    private var mAdapter: SimpleCursorAdapter? = null

    companion object {
        const val SUGGESTION_CURSOR = "users"
    }

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
                binding.llError.visible = false
                setCurrencyData(viewState.currencies)

                suggestionsList.clear()
                viewState.currencies.currencies.map {
                    with(StringExtension) {
                        suggestionsList.add(removeDiacriticalMarks(it.name))
                    }
                }
            }
            CurrencyListViewState.CurrencyListFailure -> {
                binding.progressBar.visible = false
                binding.llError.visible = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val from = arrayOf(SUGGESTION_CURSOR)
        val to = intArrayOf(android.R.id.text1)
        mAdapter = SimpleCursorAdapter(
            activity,
            R.layout.view_cell_suggestion_item,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentCurrencyListBinding
        binding.lifecycleOwner = this

        observe(cacheViewModel.stateLiveData, stateCacheObserver)
        observe(currenciesViewModel.stateLiveData, stateObserver)

        setToolbar()
        binding.rvCurrencies.setCurrenciesAdapter()

        (currenciesViewModel.stateLiveData.value as? CurrencyListViewState.CurrencyListSuccess)
            ?.currencies?.let {
                binding.progressBar.visible = false
                setCurrencyData(it)
            } ?: run {
            requestSecureData()
        }

        binding.btnTryAgain.setOnClickListener {
            requestSecureData()
        }

        binding.btnSignOut.setOnClickListener {
            cacheViewModel.signOut()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchView =
            MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        searchView.suggestionsAdapter = mAdapter
        searchView.setIconifiedByDefault(false)

        searchView.setOnSuggestionListener(object :
            SearchView.OnSuggestionListener {
            override fun onSuggestionClick(position: Int): Boolean {
                val cursor: Cursor = mAdapter?.getItem(position) as Cursor
                val txt: String = cursor.getString(cursor.getColumnIndex(SUGGESTION_CURSOR))
                searchView.setQuery(txt, true)

                currencyAdapter.items.map { uiItem ->
                    with(StringExtension) {
                        if (removeDiacriticalMarks(uiItem.name) == txt) {
                            navigator.navigateToDetails(uiItem)
                        }
                    }
                }
                return true
            }

            override fun onSuggestionSelect(position: Int): Boolean {
                return true
            }
        })
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                populateAdapter(s)
                return false
            }
        })
    }

    private fun populateAdapter(query: String) {
        val c = MatrixCursor(arrayOf(BaseColumns._ID, SUGGESTION_CURSOR))
        for (i in suggestionsList.indices) {
            if (suggestionsList[i].toLowerCase(Locale.getDefault())
                    .startsWith(query.toLowerCase(Locale.getDefault()))
            ) c.addRow(arrayOf<Any>(i, suggestionsList[i]))
        }
        mAdapter?.changeCursor(c)
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
            currenciesViewModel.postErrorAction()
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
