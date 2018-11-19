package com.akhbulatov.archsample.di.global.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.users.UsersPresenter
import dagger.Module
import dagger.Provides

@Module
class UsersModule {
    @Provides
    @UsersScope
    fun providePresenter(dataManager: DataManager, errorHandler: ErrorHandler) =
        UsersPresenter(dataManager, errorHandler)
}