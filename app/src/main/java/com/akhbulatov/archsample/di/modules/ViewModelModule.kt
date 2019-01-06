package com.akhbulatov.archsample.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.akhbulatov.archsample.presentation.global.ArchViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ArchViewModelFactory): ViewModelProvider.Factory
}