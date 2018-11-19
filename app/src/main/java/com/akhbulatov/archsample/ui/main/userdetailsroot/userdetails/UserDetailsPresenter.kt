package com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BasePresenter
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers

@InjectViewState
class UserDetailsPresenter(
    private val dataManager: DataManager,
    private val login: String,
    private val errorHandler: ErrorHandler
) : BasePresenter<UserDetailsView>() {

    override fun onFirstViewAttach() {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        dataManager.getUserDetails(login)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoadingProgress(true)
                viewState.showContentLayout(false)
            }
            .doAfterTerminate { viewState.showLoadingProgress(false) }
            .subscribe(
                {
                    viewState.showContentLayout(true)
                    viewState.showUserDetails(it)
                },
                { errorHandler.proceed(it) { viewState.showError(it) } }
            )
            .connect()
    }

    fun onAddToFavoritesClicked(userDetails: UserDetails) {
        dataManager.addUserToFavorites(userDetails)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.showToFavoritesAdded() },
                { errorHandler.proceed(it) { viewState.showError(it) } }
            )
            .connect()
    }

    fun onBackClicked() = viewState.backToUser()
}
