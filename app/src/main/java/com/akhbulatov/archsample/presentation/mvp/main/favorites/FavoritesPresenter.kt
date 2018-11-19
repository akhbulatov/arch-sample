package com.akhbulatov.archsample.presentation.mvp.main.favorites

import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.global.ResourceManager
import com.akhbulatov.archsample.domain.main.favorites.FavoritesInteractor
import com.akhbulatov.archsample.presentation.mvp.global.BasePresenter
import com.akhbulatov.archsample.presentation.mvp.global.ErrorHandler
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class FavoritesPresenter(
    router: Router,
    private val interactor: FavoritesInteractor,
    private val errorHandler: ErrorHandler,
    private val resourceManager: ResourceManager
) : BasePresenter<FavoritesView>(router) {

    override fun onFirstViewAttach() {
        loadFavorites()
    }

    private fun loadFavorites() {
        interactor.getFavorites()
            .doOnSubscribe { viewState.showLoadingProgress(true) }
            .doAfterTerminate { viewState.showLoadingProgress(false) }
            .subscribe(
                {
                    if (!it.isEmpty()) {
                        viewState.showFavorites(it)
                    } else {
                        viewState.showError(resourceManager.getString(R.string.msg_empty_list))
                    }
                },
                { errorHandler.proceed(it) { viewState.showError(it) } }
            )
            .connect()
    }
}