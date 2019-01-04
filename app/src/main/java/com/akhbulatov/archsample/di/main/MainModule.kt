package com.akhbulatov.archsample.di.main

import com.akhbulatov.archsample.presentation.mvp.main.MainPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainModule {
    @Provides
    @MainScope
    fun providePresenter(router: Router) = MainPresenter(router)
}