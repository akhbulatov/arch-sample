package com.akhbulatov.archsample.presentation.ui.main.userdetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.akhbulatov.archsample.domain.main.userdetails.UserDetailsInteractor
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.presentation.global.BaseViewModel
import com.akhbulatov.archsample.presentation.global.ErrorHandler
import com.akhbulatov.archsample.presentation.global.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    router: Router,
    private val interactor: UserDetailsInteractor,
    private val errorHandler: ErrorHandler
) : BaseViewModel(router) {

    private val _login = MutableLiveData<String>()

    private lateinit var _userDetails: MutableLiveData<UserDetails>
    val userDetails: LiveData<UserDetails>
        get() {
            if (!::_userDetails.isInitialized && _login.value != null) {
                _userDetails = MutableLiveData()
                loadUserDetails(_login.value!!)
            }
            return _userDetails
        }

    private val _showContentLayout = MutableLiveData<Boolean>()
    val showContentLayout: LiveData<Boolean> get() = _showContentLayout

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean> get() = _loadingProgress

    private val _loadingError = MutableLiveData<String>()
    val loadingError: LiveData<String> get() = _loadingError

    private val _addedToFavorites = SingleLiveEvent<Unit>()
    val addedToFavorites: LiveData<Unit> get() = _addedToFavorites

    fun setLogin(login: String) {
        if (_login.value != login) {
            _login.value = login
        }
    }

    private fun loadUserDetails(login: String) {
        interactor.getUserDetails(login)
            .doOnSubscribe {
                _showContentLayout.value = false
                _loadingProgress.value = true
            }
            .doAfterTerminate { _loadingProgress.value = false }
            .subscribe(
                {
                    _showContentLayout.value = true
                    _userDetails.value = it!!
                },
                { errorHandler.proceed(it) { msg -> _loadingError.value = msg } }
            )
            .connect()
    }

    fun onAddToFavoritesClicked(userDetails: UserDetails) {
        interactor.addUserToFavorites(userDetails)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _addedToFavorites.call() },
                { errorHandler.proceed(it) { msg -> _loadingError.value = msg } }
            )
            .connect()
    }
}
