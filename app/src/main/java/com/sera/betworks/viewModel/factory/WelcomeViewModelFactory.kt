@file:Suppress("UNCHECKED_CAST")

package com.sera.betworks.viewModel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sera.betworks.viewModel.WelcomeViewModel

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}