package com.akhbulatov.archsample.ui.global

import android.support.v4.app.Fragment
import com.akhbulatov.archsample.ui.main.favorites.FavoritesFragment
import com.akhbulatov.archsample.ui.main.userdetails.UserDetailsFragment
import com.akhbulatov.archsample.ui.main.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object Users : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return UsersFragment()
        }
    }

    data class UserDetails(private val login: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return UserDetailsFragment.newInstance(login)
        }
    }

    object Favorites : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FavoritesFragment()
        }
    }
}