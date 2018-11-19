package com.akhbulatov.archsample.ui.main.userdetails

import com.akhbulatov.archsample.models.UserDetails
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailsView : MvpView {
    fun showContentLayout(show: Boolean)
    fun showUserDetails(userDetails: UserDetails)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToFavoritesAdded()
}