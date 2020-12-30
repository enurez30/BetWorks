package com.sera.betworks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sera.betworks.R

class MainActivity : AppCompatActivity() {

    /**`
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState?.getBoolean("changed") != true) {
            replaceFragment(fragment = LoginFragment.newInstance())
        }
    }

    /**
     *
     */
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.simpleName)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
    }

    /**
     *
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("changed", true)
    }

    /**
     *
     */
    override fun onBackPressed() {
        with(supportFragmentManager) {
            this.findFragmentByTag(WelcomeFragment::class.java.simpleName)?.let {
                this.popBackStack()
            } ?: kotlin.run {
                showExitDialog()
            }
        }
    }

    /**
     *
     */
    private fun showExitDialog() {

        MaterialAlertDialogBuilder(this, R.style.MaterialAlertStyle)
            .setMessage(this.resources.getString(R.string.exit_text))
            .setPositiveButton("Leave") { _, _ ->
                finish()
            }
            .setNegativeButton("Cancel") { _, _ ->
                // nothing
            }
            .show()

    }
}