package com.akhbulatov.archsample.presentation.ui.main.users

import android.arch.lifecycle.ViewModel
import com.akhbulatov.archsample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UsersModule {
    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    abstract fun bindUsersViewModel(viewModel: UsersViewModel): ViewModel
}