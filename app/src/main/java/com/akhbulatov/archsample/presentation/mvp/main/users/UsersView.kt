package com.akhbulatov.archsample.presentation.mvp.main.users

import com.akhbulatov.archsample.domain.models.User
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun showUsers(users: List<User>)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)
}