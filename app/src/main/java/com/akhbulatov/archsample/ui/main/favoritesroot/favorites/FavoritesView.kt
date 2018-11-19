package com.akhbulatov.archsample.ui.main.favoritesroot.favorites

import com.akhbulatov.archsample.models.UserDetails
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoritesView : MvpView {
    fun showFavorites(favorites: List<UserDetails>)
    fun showLoadingProgress(show: Boolean)
    fun showEmptyFavorites()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun backToUsers()
}