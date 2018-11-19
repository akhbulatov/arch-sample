package com.akhbulatov.archsample.ui.main.favoritesroot.favorites

import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BaseView

interface FavoritesView : BaseView {
    fun showFavorites(favorites: List<UserDetails>)
    fun showLoadingProgress(show: Boolean)
    fun showEmptyFavorites()
    fun backToUsers()
}