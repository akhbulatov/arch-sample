package com.akhbulatov.githubsample.ui.main.users

import com.akhbulatov.githubsample.models.User
import com.akhbulatov.githubsample.ui.global.BaseView

interface UsersView : BaseView {
    fun showUsers(users: List<User>)
    fun showLoadingProgress(show: Boolean)
    fun navigateToUserDetails(user: User)
    fun navigateToFavorites()
}