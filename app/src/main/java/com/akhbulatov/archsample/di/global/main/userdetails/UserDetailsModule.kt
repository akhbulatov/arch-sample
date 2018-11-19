package com.akhbulatov.archsample.di.global.main.userdetails

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.userdetails.UserDetailsPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class UserDetailsModule {
    @Provides
    @UserDetailsScope
    fun providePresenter(
        router: Router,
        dataManager: DataManager,
        login: String,
        errorHandler: ErrorHandler
    ) = UserDetailsPresenter(router, dataManager, login, errorHandler)
}