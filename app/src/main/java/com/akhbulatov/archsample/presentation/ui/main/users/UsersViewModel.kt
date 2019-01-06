package com.akhbulatov.archsample.presentation.ui.main.users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.akhbulatov.archsample.domain.main.users.UsersInteractor
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.presentation.global.BaseViewModel
import com.akhbulatov.archsample.presentation.global.ErrorHandler
import com.akhbulatov.archsample.presentation.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val router: Router,
    private val interactor: UsersInteractor,
    private val errorHandler: ErrorHandler
) : BaseViewModel(router) {

    private lateinit var _users: MutableLiveData<List<User>>
    val users: LiveData<List<User>>
        get() {
            if (!::_users.isInitialized) {
                _users = MutableLiveData()
                loadUsers()
            }
            return _users
        }

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean> get() = _loadingProgress

    private val _loadingError = MutableLiveData<String>()
    val loadingError: LiveData<String> get() = _loadingError

    private fun loadUsers() {
        interactor.getUsers()
            .doOnSubscribe { _loadingProgress.value = true }
            .doAfterTerminate { _loadingProgress.value = false }
            .subscribe(
                { _users.value = it },
                { errorHandler.proceed(it) { msg -> _loadingError.value = msg } }
            )
            .connect()
    }

    fun onUserClicked(user: User) = router.navigateTo(Screens.UserDetails(user.login))

    fun onFavoritesClicked() = router.navigateTo(Screens.Favorites)
}