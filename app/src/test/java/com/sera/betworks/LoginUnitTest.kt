package com.sera.betworks

import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class LoginUnitTest {

    @Test
    fun userNameValidity() {
        assertThat(String.format("Name Validity Test failed for %s ", User.userName), isValidateInput(User.userName.value ?: "n1"), CoreMatchers.`is`(true))
    }

    @Test
    fun userPasswordValidity() {
        assertThat(String.format("Password Validity Test failed for %s ", User.userPassword), isValidateInput(User.userPassword.value ?: "p1"), CoreMatchers.`is`(true))
    }

    @Test
    fun fieldsValidity() {
        val name = "n1"
        val password = "p1"
        assertTrue(isValidateInput(name) && isValidateInput(password))
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