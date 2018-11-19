package com.akhbulatov.archsample.ui.main.userdetailsroot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails.UserDetailsFragment

class UserDetailsRootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (intent.extras == null) {
            throw IllegalArgumentException("Must pass login extra.")
        }
        val userLogin = intent.getStringExtra(EXTRA_LOGIN)
        navigateToUserDetails(userLogin)
    }

    private fun navigateToUserDetails(userLogin: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserDetailsFragment.newInstance(userLogin))
            .commit()
    }

    companion object {
        private const val EXTRA_LOGIN = "login"

        fun createIntent(context: Context, login: String): Intent =
            Intent(context, UserDetailsRootActivity::class.java).apply {
                putExtra(EXTRA_LOGIN, login)
            }
    }
}