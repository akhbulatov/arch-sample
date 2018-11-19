package com.akhbulatov.archsample.ui.main

import android.content.Intent
import android.os.Bundle
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.ui.main.favoritesroot.FavoritesRootActivity
import com.akhbulatov.archsample.ui.main.userdetailsroot.UserDetailsRootActivity
import com.akhbulatov.archsample.ui.main.users.UsersFragment
import com.arellomobile.mvp.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), UsersFragment.UsersListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        navigateToUsers()
    }

    private fun navigateToUsers() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UsersFragment())
            .commit()
    }

    override fun onUserClick(user: User) = navigateToUserDetails(user)

    override fun onFavoritesClick() = navigateToFavorites()

    private fun navigateToUserDetails(user: User) {
        startActivity(UserDetailsRootActivity.createIntent(this, user.login))
    }

    private fun navigateToFavorites() {
        startActivity(Intent(this, FavoritesRootActivity::class.java))
    }
}