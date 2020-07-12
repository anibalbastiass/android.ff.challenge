package com.ff.challenge.feature.currencies.ui.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.navArgs
import com.ff.challenge.feature.currencies.R
import com.ff.challenge.feature.currencies.databinding.FragmentCurrencyDetailsBinding
import com.ff.challenge.library.base.presentation.extension.ToolbarExtension
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment

class CurrencyDetailsFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_currency_details
    private lateinit var binding: FragmentCurrencyDetailsBinding
    private val args: CurrencyDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentCurrencyDetailsBinding
        binding.lifecycleOwner = this

        setToolbar()
        binding.item = args.item
    }

    private fun setToolbar() {
        binding.tbCurrencies.apply {
            with(ToolbarExtension) {
                applyFontForToolbarTitle(requireActivity())
                setArrowUpToolbar(requireActivity())
                setNavigationOnClickListener {
                    activity?.onBackPressed()
                }
            }
            title = args.item.name
        }
    }
}
