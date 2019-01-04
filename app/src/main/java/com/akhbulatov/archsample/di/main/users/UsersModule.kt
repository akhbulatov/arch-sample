package com.akhbulatov.archsample.di.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.users.UsersPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class UsersModule {
    @Provides
    @UsersScope
    fun providePresenter(router: Router, dataManager: DataManager, errorHandler: ErrorHandler) =
        UsersPresenter(router, dataManager, errorHandler)
}