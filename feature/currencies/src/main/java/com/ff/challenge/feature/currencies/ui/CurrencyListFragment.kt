package com.ff.challenge.feature.currencies.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.navArgs
import com.ff.challenge.feature.currencies.R
import com.ff.challenge.feature.currencies.databinding.FragmentCurrencyListBinding
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment

class CurrencyListFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_currency_list
    private lateinit var binding: FragmentCurrencyListBinding
    private val args: CurrencyListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentCurrencyListBinding
        binding.lifecycleOwner = this

        binding.tvTitle.text = args.email

    }

}