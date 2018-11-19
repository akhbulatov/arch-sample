package com.akhbulatov.archsample.di.global.main.userdetailsroot.userdetails

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails.UserDetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class UserDetailsModule {
    @Provides
    @UserDetailsScope
    fun providePresenter(dataManager: DataManager, login: String, errorHandler: ErrorHandler) =
        UserDetailsPresenter(dataManager, login, errorHandler)
}