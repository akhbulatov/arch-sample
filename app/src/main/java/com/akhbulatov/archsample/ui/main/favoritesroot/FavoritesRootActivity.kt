package com.akhbulatov.archsample.ui.main.favoritesroot

import android.os.Bundle
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.ui.main.favoritesroot.favorites.FavoritesFragment
import com.arellomobile.mvp.MvpAppCompatActivity

class FavoritesRootActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        navigateToFavorites()
    }

    private fun navigateToFavorites() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FavoritesFragment())
            .commit()
    }
}