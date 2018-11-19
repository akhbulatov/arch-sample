package com.akhbulatov.archsample.di.global.main.favoritesroot.favorites

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.main.favoritesroot.favorites.FavoritesPresenter
import dagger.Module
import dagger.Provides

@Module
class FavoritesModule {
    @Provides
    @FavoritesScope
    fun providePresenter(dataManager: DataManager) = FavoritesPresenter(dataManager)
}