package com.akhbulatov.archsample.ui.main.favoritesroot.favorites

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BasePresenter
import com.arellomobile.mvp.InjectViewState
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    dataManager: DataManager
) : BasePresenter<FavoritesView>() {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val favoritesCallable: Callable<List<UserDetails>> =
        Callable { dataManager.getFavorites() }

    override fun onFirstViewAttach() {
        loadFavorites()
    }

    override fun onDestroy() {
        executor.shutdown()
        super.onDestroy()
    }

    private fun loadFavorites() {
        viewState.showLoadingProgress(true)
        val favorites = executor.submit(favoritesCallable).get()
        viewState.showLoadingProgress(false)

        if (!favorites.isEmpty()) {
            viewState.showFavorites(favorites)
        } else {
            viewState.showEmptyFavorites()
        }
    }

    fun onBackClicked() = viewState.backToUsers()
}