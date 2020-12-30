package com.sera.betworks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialSharedAxis
import com.sera.betworks.R
import com.sera.betworks.User
import com.sera.betworks.viewModel.WelcomeViewModel
import com.sera.betworks.viewModel.factory.WelcomeViewModelFactory
import com.sera.betworks.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    private lateinit var mBinder: WelcomeFragmentBinding

    /**
     *
     */
    companion object {
        fun newInstance() = WelcomeFragment()
    }

    /**
     *
     */
    private val viewModel: WelcomeViewModel by viewModels {
        WelcomeViewModelFactory()
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
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)
        return mBinder.root
    }

    /**
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinder.user = User
    }
}