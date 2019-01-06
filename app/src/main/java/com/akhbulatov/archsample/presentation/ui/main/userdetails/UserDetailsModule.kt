package com.akhbulatov.archsample.presentation.ui.main.userdetails

import android.arch.lifecycle.ViewModel
import com.akhbulatov.archsample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel
}