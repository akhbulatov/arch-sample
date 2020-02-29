package com.akhbulatov.archsample.di.main.favoritesroot.favorites

import com.akhbulatov.archsample.ui.main.favoritesroot.favorites.FavoritesPresenter
import toothpick.config.Module

class FavoritesModule : Module() {
    init {
        bind(FavoritesPresenter::class.java)
    }
}