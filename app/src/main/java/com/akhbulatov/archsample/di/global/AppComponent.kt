package com.akhbulatov.archsample.di.global

import android.content.Context
import com.akhbulatov.archsample.di.global.modules.DataModule
import com.akhbulatov.archsample.di.global.modules.DatabaseModule
import com.akhbulatov.archsample.di.global.modules.NetworkModule
import com.akhbulatov.archsample.di.main.favoritesroot.favorites.FavoritesComponent
import com.akhbulatov.archsample.di.main.userdetailsroot.userdetails.UserDetailsComponent
import com.akhbulatov.archsample.di.main.users.UsersComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class, DataModule::class])
@Singleton
interface AppComponent {
    fun usersComponentBuilder(): UsersComponent.Builder
    fun userDetailsComponentBuilder(): UserDetailsComponent.Builder
    fun favoritesComponentBuilder(): FavoritesComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance fun context(context: Context): Builder

        fun build(): AppComponent
    }
}