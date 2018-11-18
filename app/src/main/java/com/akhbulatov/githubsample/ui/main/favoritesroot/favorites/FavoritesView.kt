package com.akhbulatov.githubsample.ui.main.favoritesroot.favorites

import com.akhbulatov.githubsample.models.UserDetails
import com.akhbulatov.githubsample.ui.global.BaseView

interface FavoritesView : BaseView {
    fun showFavorites(favorites: List<UserDetails>)
    fun showLoadingProgress(show: Boolean)
    fun showEmptyFavorites()
    fun backToUsers()
}