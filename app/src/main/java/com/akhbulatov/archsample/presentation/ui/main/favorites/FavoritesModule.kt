package com.akhbulatov.archsample.presentation.ui.main.favorites

import android.arch.lifecycle.ViewModel
import com.akhbulatov.archsample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoritesModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel
}