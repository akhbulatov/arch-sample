package com.akhbulatov.archsample.presentation.mvp.main.userdetails

import com.akhbulatov.archsample.domain.main.userdetails.UserDetailsInteractor
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.presentation.mvp.global.BasePresenter
import com.akhbulatov.archsample.presentation.mvp.global.ErrorHandler
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router

@InjectViewState
class UserDetailsPresenter(
    router: Router,
    private val interactor: UserDetailsInteractor,
    private val login: String,
    private val errorHandler: ErrorHandler
) : BasePresenter<UserDetailsView>(router) {

    override fun onFirstViewAttach() {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        interactor.getUserDetails(login)
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
        interactor.addUserToFavorites(userDetails)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.showToFavoritesAdded() },
                { errorHandler.proceed(it) { viewState.showError(it) } }
            )
            .connect()
    }
}
