package com.ff.challenge.feature.auth.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment
import com.ff.challenge.feature.auth.R
import com.ff.challenge.feature.auth.databinding.FragmentSignInBinding

class SignInFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_sign_in
    private lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentSignInBinding
        binding.lifecycleOwner = this
    }

}