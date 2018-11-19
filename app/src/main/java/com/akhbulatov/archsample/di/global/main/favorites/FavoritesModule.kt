package com.akhbulatov.archsample.di.global.main.favorites

import com.akhbulatov.archsample.domain.global.ResourceManager
import com.akhbulatov.archsample.domain.main.favorites.FavoritesInteractor
import com.akhbulatov.archsample.presentation.mvp.global.ErrorHandler
import com.akhbulatov.archsample.presentation.mvp.main.favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class FavoritesModule {
    @Provides
    @FavoritesScope
    fun providePresenter(
        router: Router,
        interactor: FavoritesInteractor,
        errorHandler: ErrorHandler,
        resourceManager: ResourceManager
    ) = FavoritesPresenter(router, interactor, errorHandler, resourceManager)
}