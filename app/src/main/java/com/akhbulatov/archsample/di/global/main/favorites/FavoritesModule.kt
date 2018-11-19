package com.akhbulatov.archsample.di.global.main.favorites

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class FavoritesModule {
    @Provides
    @FavoritesScope
    fun providePresenter(router: Router, dataManager: DataManager, errorHandler: ErrorHandler) =
        FavoritesPresenter(router, dataManager, errorHandler)
}