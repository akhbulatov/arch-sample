package com.akhbulatov.archsample.di.global.main

import com.akhbulatov.archsample.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainModule {
    @Provides
    @MainScope
    fun providePresenter(router: Router) = MainPresenter(router)
}