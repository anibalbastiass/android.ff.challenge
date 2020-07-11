package com.ff.challenge.feature.auth.ui.signin

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ff.challenge.feature.auth.AuthNavigator
import com.ff.challenge.feature.auth.R
import com.ff.challenge.feature.auth.databinding.FragmentSignInBinding
import com.ff.challenge.feature.auth.presentation.viewmodel.SignInViewModel
import com.ff.challenge.feature.auth.presentation.viewstate.SignInViewState
import com.ff.challenge.library.base.presentation.extension.WidgetExtension
import com.ff.challenge.library.base.presentation.extension.isNetworkAvailable
import com.ff.challenge.library.base.presentation.extension.observe
import com.ff.challenge.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.toast
import com.pawegio.kandroid.visible
import org.kodein.di.generic.instance

class SignInFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_sign_in
    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by instance()
    private val navigator: AuthNavigator by instance()
    private val connectionManager: ConnectivityManager by instance()

    private val stateObserver = Observer<SignInViewState> { viewState ->
        when (viewState) {
            SignInViewState.InitialState -> {
                binding.progressBar.visible = false
            }
            is SignInViewState.UserFound -> {
                binding.progressBar.visible = false

                viewState.user.apply {
                    navigator.navigateToCurrencies(email, fullName)
                }
            }
            SignInViewState.NotUserFound -> {
                binding.progressBar.visible = false
                activity?.toast(R.string.login_failed)
            }
            is SignInViewState.InvalidEmail -> {
                binding.btnSignIn.isEnabled = false
                binding.etEmail.error = getString(viewState.error)
            }
            is SignInViewState.InvalidPassword -> {
                binding.btnSignIn.isEnabled = false
                binding.etPassword.error = getString(viewState.error)
            }
            is SignInViewState.ValidDataUser -> {
                binding.btnSignIn.isEnabled = true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind<ViewDataBinding>(view) as FragmentSignInBinding
        binding.lifecycleOwner = this

        observe(viewModel.stateLiveData, stateObserver)

        binding.apply {
            with(WidgetExtension) {
                etEmail.onTextChanged {
                    onChangeEditTexts()
                }
                etPassword.onTextChanged {
                    onChangeEditTexts()
                }
            }

            etPassword.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signInAction()
                }
                false
            }

            btnSignIn.setOnClickListener {
                signInAction()
            }
        }
    }

    private fun onChangeEditTexts() {
        binding.apply {
            viewModel.signInDataChanged(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun signInAction() {
        if (connectionManager.isNetworkAvailable()) {
            binding.apply {
                progressBar.visible = true
                viewModel.signIn(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        } else {
            activity?.toast(R.string.error_connection_internet)
        }
    }
}
