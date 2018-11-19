package com.akhbulatov.archsample.ui.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.ui.global.BasePresenter
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.global.Screens
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router

@InjectViewState
class UsersPresenter(
    private val router: Router,
    private val dataManager: DataManager,
    private val errorHandler: ErrorHandler
) : BasePresenter<UsersView>(router) {

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

    fun onUserClicked(user: User) = router.navigateTo(Screens.UserDetails(user.login))

    fun onFavoritesClicked() = router.navigateTo(Screens.Favorites)
}