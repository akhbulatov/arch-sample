package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails

import com.akhbulatov.githubsample.models.UserDetails
import com.akhbulatov.githubsample.ui.global.BaseView

interface UserDetailsView : BaseView {
    fun showContentLayout(show: Boolean)
    fun showUserDetails(userDetails: UserDetails)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)
    fun showToFavoritesAdded()
    fun backToUser()
}