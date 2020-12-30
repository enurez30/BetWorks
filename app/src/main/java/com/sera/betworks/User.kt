package com.sera.betworks

import androidx.lifecycle.MutableLiveData

object User {

    private var name = MutableLiveData<String>().apply {
        value = ""
    }

    private var password = MutableLiveData<String>().apply {
        value = ""
    }

    var userName: MutableLiveData<String>
        get() = name
        set(value) {
            name = value
        }


    var userPassword: MutableLiveData<String>
        get() = password
        set(value) {
            password = value
        }
}