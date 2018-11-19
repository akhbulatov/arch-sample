package com.akhbulatov.archsample.presentation.mvp.main.users

import com.akhbulatov.archsample.domain.main.users.UsersInteractor
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.presentation.mvp.global.BasePresenter
import com.akhbulatov.archsample.presentation.mvp.global.ErrorHandler
import com.akhbulatov.archsample.presentation.mvp.global.Screens
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class UsersPresenter(
    private val router: Router,
    private val interactor: UsersInteractor,
    private val errorHandler: ErrorHandler
) : BasePresenter<UsersView>(router) {

    override fun onFirstViewAttach() {
        loadUsers()
    }

    private fun loadUsers() {
        interactor.getUsers()
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