package com.akhbulatov.archsample.presentation.ui.main

import android.arch.lifecycle.ViewModel
import com.akhbulatov.archsample.di.ViewModelKey
import com.akhbulatov.archsample.di.scope.FragmentScope
import com.akhbulatov.archsample.presentation.ui.main.favorites.FavoritesFragment
import com.akhbulatov.archsample.presentation.ui.main.favorites.FavoritesModule
import com.akhbulatov.archsample.presentation.ui.main.userdetails.UserDetailsFragment
import com.akhbulatov.archsample.presentation.ui.main.userdetails.UserDetailsModule
import com.akhbulatov.archsample.presentation.ui.main.users.UsersFragment
import com.akhbulatov.archsample.presentation.ui.main.users.UsersModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @FragmentScope
    @ContributesAndroidInjector(modules = [UsersModule::class])
    abstract fun contributeUsersFragment(): UsersFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UserDetailsModule::class])
    abstract fun contributeUserDetailsFragment(): UserDetailsFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FavoritesModule::class])
    abstract fun contributeFavoritesFragment(): FavoritesFragment
}