package com.sera.betworks.viewModel

import androidx.lifecycle.ViewModel
import com.sera.betworks.User


class LoginViewModel : ViewModel() {

    /**
     *
     */
    fun updateUser(name: String, password: String) {
        User.userName.value = name
        User.userPassword.value = password
    }

    /**
     *
     */
    fun validateLogin(name: String, password: String): Boolean {
        return (isValidateInput(value = name) && isValidateInput(value = password))
    }

    /**
     *
     */
    private fun isValidateInput(value: String): Boolean {
        val n = ".*[0-9].*".toRegex()
        val a = ".*[a-z,A-Z].*".toRegex()
        return value.matches(n) && value.matches(a) && value.length >= 2
    }


}

