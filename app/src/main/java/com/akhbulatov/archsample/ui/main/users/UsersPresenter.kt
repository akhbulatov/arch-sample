package com.akhbulatov.archsample.ui.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.ui.global.BasePresenter
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.global.Screens
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import me.aartikov.alligator.Navigator

@InjectViewState
class UsersPresenter(
    private val navigator: Navigator,
    private val dataManager: DataManager,
    private val errorHandler: ErrorHandler
) : BasePresenter<UsersView>(navigator) {

    override fun onFirstViewAttach() {
        loadUsers()
    }

    private fun loadUsers() {
        dataManager.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoadingProgress(true) }
            .doAfterTerminate { viewState.showLoadingProgress(false) }
            .subscribe(
                { viewState.showUsers(it) },
                { errorHandler.proceed(it) { viewState.showError(it) } }
            )
            .connect()
    }

    fun onUserClicked(user: User) = navigator.goForward(Screens.UserDetails(user.login))

    fun onFavoritesClicked() = navigator.goForward(Screens.Favorites)
}