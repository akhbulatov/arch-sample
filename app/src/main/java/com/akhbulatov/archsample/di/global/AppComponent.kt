package com.akhbulatov.archsample.di.global

import android.content.Context
import com.akhbulatov.archsample.di.global.main.MainComponent
import com.akhbulatov.archsample.di.global.main.favorites.FavoritesComponent
import com.akhbulatov.archsample.di.global.main.userdetails.UserDetailsComponent
import com.akhbulatov.archsample.di.global.main.users.UsersComponent
import com.akhbulatov.archsample.di.global.modules.DataModule
import com.akhbulatov.archsample.di.global.modules.DatabaseModule
import com.akhbulatov.archsample.di.global.modules.NavigationModule
import com.akhbulatov.archsample.di.global.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        DataModule::class,
        NavigationModule::class
    ]
)
@Singleton
interface AppComponent {
    fun mainComponentBuilder(): MainComponent.Builder
    fun usersComponentBuilder(): UsersComponent.Builder
    fun userDetailsComponentBuilder(): UserDetailsComponent.Builder
    fun favoritesComponentBuilder(): FavoritesComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance fun context(context: Context): Builder

        fun build(): AppComponent
    }
}