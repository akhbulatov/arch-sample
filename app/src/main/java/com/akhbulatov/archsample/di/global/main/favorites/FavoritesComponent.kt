package com.akhbulatov.archsample.di.global.main.favorites

import com.akhbulatov.archsample.ui.main.favorites.FavoritesFragment
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