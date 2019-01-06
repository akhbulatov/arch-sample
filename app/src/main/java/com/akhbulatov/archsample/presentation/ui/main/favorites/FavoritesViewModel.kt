package com.akhbulatov.archsample.presentation.ui.main.favorites

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.global.ResourceManager
import com.akhbulatov.archsample.domain.main.favorites.FavoritesInteractor
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.presentation.global.BaseViewModel
import com.akhbulatov.archsample.presentation.global.ErrorHandler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    router: Router,
    private val interactor: FavoritesInteractor,
    private val errorHandler: ErrorHandler,
    private val resourceManager: ResourceManager
) : BaseViewModel(router) {

    private lateinit var _favorites: MutableLiveData<List<UserDetails>>
    val favorites: LiveData<List<UserDetails>>
        get() {
            if (!::_favorites.isInitialized) {
                _favorites = MutableLiveData()
                loadFavorites()
            }
            return _favorites
        }

    private val _loadingProgress = MutableLiveData<Boolean>()
    val loadingProgress: LiveData<Boolean> get() = _loadingProgress

    private val _loadingError = MutableLiveData<String>()
    val loadingError: LiveData<String> get() = _loadingError

    private fun loadFavorites() {
        interactor.getFavorites()
            .doOnSubscribe { _loadingProgress.value = true }
            .doAfterTerminate { _loadingProgress.value = false }
            .subscribe(
                {
                    if (!it.isEmpty()) {
                        _favorites.value = it
                    } else {
                        _loadingError.value = resourceManager.getString(R.string.msg_empty_list)
                    }
                },
                { errorHandler.proceed(it) { msg -> _loadingError.value = msg } }
            )
            .connect()
    }
}