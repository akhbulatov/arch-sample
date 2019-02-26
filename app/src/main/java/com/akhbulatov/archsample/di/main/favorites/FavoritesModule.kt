package com.akhbulatov.archsample.di.main.favorites

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides
import me.aartikov.alligator.Navigator

@Module
class FavoritesModule {
    @Provides
    @FavoritesScope
    fun providePresenter(
        navigator: Navigator,
        dataManager: DataManager,
        errorHandler: ErrorHandler
    ) = FavoritesPresenter(navigator, dataManager, errorHandler)
}