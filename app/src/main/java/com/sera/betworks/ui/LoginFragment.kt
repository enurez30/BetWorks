package com.sera.betworks.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialSharedAxis
import com.sera.betworks.R
import com.sera.betworks.User
import com.sera.betworks.databinding.LoginFragmentBinding
import com.sera.betworks.network.HttpClient
import com.sera.betworks.network.SchedulerProvider
import com.sera.betworks.utils.Handlers
import com.sera.betworks.viewModel.LoginViewModel
import com.sera.betworks.viewModel.factory.LoginViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment(), Handlers {

    private lateinit var mBinder: LoginFragmentBinding

    /**
     *
     */
    companion object {
        fun newInstance() = LoginFragment()
    }

    /**
     *
     */
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory()
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val forward = MaterialSharedAxis(MaterialSharedAxis.X, true)
        enterTransition = forward

        val backward = MaterialSharedAxis(MaterialSharedAxis.X, false)
        returnTransition = backward
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        return mBinder.root
    }

    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinder.handlers = this
        mBinder.user = User
        mBinder.swipeLayout.setColorSchemeColors(Color.GREEN, Color.BLUE)
    }

    /**
     *
     */
    override fun onHandlerClicked(view: View) {
        when (view.id) {
            R.id.loginBtn -> {
                if (viewModel.validateLogin(name = mBinder.nameET.text.toString(), password = mBinder.passwordET.text.toString())) {
                    lifecycleScope.launch(Dispatchers.Main) {
                        waitAnimation(isShow = true)
                        delay(2000)
                        getUser()
                    }
                } else {
                    showActionMessage(contentValue = "Error, Please try again")
                }
            }
        }
    }

    /**
     *
     */
    private fun showActionMessage(contentValue: String) {
        val builder = StringBuilder()
        builder.append(contentValue).append("\n").append("")
        MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertStyle)
            .setMessage(builder.toString())
            .setPositiveButton("DISMISS") { _, _ ->
                // Respond to positive button press
            }
            .show()

    }

    /**
     *
     */
    @SuppressLint("CheckResult")
    private fun getUser() {
        println("rxCall HTTP Request in progress.")
        HttpClient.getApiService()?.getUser()
            ?.observeOn(SchedulerProvider.ui())
            ?.subscribeOn(SchedulerProvider.io())
            ?.subscribe(
                {
                    println("rxCall Request Completed! ")
                    viewModel.updateUser(name = mBinder.nameET.text.toString(), password = mBinder.passwordET.text.toString())
                    (requireActivity() as MainActivity).replaceFragment(fragment = WelcomeFragment.newInstance())
                    waitAnimation(isShow = false)
                })
            { t ->
                println("rxCall ERROR! ${t.message}")
                showActionMessage(contentValue = "Network Error")
                waitAnimation(isShow = false)
            }
    }

    /**
     *
     */
    private fun waitAnimation(isShow: Boolean) {
        mBinder.swipeLayout.isRefreshing = isShow
        if (isShow) {
            mBinder.overlay.visibility = View.VISIBLE
        } else {
            mBinder.overlay.visibility = View.GONE
        }
    }
}