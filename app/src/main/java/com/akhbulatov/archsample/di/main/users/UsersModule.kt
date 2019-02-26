package com.akhbulatov.archsample.di.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.main.users.UsersPresenter
import dagger.Module
import dagger.Provides
import me.aartikov.alligator.Navigator

@Module
class UsersModule {
    @Provides
    @UsersScope
    fun providePresenter(
        navigator: Navigator,
        dataManager: DataManager,
        errorHandler: ErrorHandler
    ) = UsersPresenter(navigator, dataManager, errorHandler)
}