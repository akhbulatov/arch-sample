package com.akhbulatov.archsample.di.global.main.favoritesroot.favorites

import com.akhbulatov.archsample.ui.main.favoritesroot.favorites.FavoritesFragment
import dagger.Subcomponent

@Subcomponent(modules = [FavoritesModule::class])
@FavoritesScope
interface FavoritesComponent {
    fun inject(fragment: FavoritesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): FavoritesComponent
    }
}