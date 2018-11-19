package com.akhbulatov.archsample.ui.main.users

import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.ui.global.BaseView

interface UsersView : BaseView {
    fun showUsers(users: List<User>)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)
    fun navigateToUserDetails(user: User)
    fun navigateToFavorites()
}