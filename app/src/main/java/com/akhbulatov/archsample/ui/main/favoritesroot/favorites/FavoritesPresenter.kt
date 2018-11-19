package com.akhbulatov.archsample.ui.main.favoritesroot.favorites

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BasePresenterImpl
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoritesPresenter(dataManager: DataManager) : BasePresenterImpl<FavoritesView>() {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val favoritesCallable: Callable<List<UserDetails>> =
        Callable { dataManager.getFavorites() }

    override fun attachView(view: FavoritesView) {
        super.attachView(view)
        loadFavorites()
    }

    override fun detachView() {
        executor.shutdown()
        super.detachView()
    }

    private fun loadFavorites() {
        view?.let {
            it.showLoadingProgress(true)

            val favorites = executor.submit(favoritesCallable).get()

            view?.let {
                it.showLoadingProgress(false)

                if (!favorites.isEmpty()) {
                    it.showFavorites(favorites)
                } else {
                    it.showEmptyFavorites()
                }
            }
        }
    }

    fun onBackClicked() = view?.backToUsers()
}