package com.ff.challenge.feature.currencies.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ff.challenge.feature.currencies.databinding.FragmentCurrencyListBinding
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment

class CurrencyListFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = com.ff.challenge.feature.currencies.R.layout.fragment_currency_list
    private lateinit var binding: FragmentCurrencyListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentCurrencyListBinding
        binding.lifecycleOwner = this
    }

}