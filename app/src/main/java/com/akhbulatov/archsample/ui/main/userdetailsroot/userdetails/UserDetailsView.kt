package com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails

import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BaseView

interface UserDetailsView : BaseView {
    fun showContentLayout(show: Boolean)
    fun showUserDetails(userDetails: UserDetails)
    fun showLoadingProgress(show: Boolean)
    fun showError(error: String)
    fun showToFavoritesAdded()
    fun backToUser()
}