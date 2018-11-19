package com.akhbulatov.archsample.ui.main.favoritesroot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.ui.main.favoritesroot.favorites.FavoritesFragment

class FavoritesRootActivity : AppCompatActivity() {

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