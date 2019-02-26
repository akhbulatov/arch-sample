package com.akhbulatov.archsample.di.main

import com.akhbulatov.archsample.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import me.aartikov.alligator.Navigator

@Module
class MainModule {
    @Provides
    @MainScope
    fun providePresenter(navigator: Navigator) = MainPresenter(navigator)
}