package com.akhbulatov.archsample.ui.main.users

import com.akhbulatov.archsample.models.User
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun showUsers(users: List<User>)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToUserDetails(user: User)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToFavorites()
}