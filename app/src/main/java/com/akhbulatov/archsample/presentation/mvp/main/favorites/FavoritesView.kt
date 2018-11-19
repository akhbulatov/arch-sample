package com.akhbulatov.archsample.presentation.mvp.main.favorites

import com.akhbulatov.archsample.domain.models.UserDetails
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoritesView : MvpView {
    fun showFavorites(favorites: List<UserDetails>)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)
}