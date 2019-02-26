package com.akhbulatov.archsample.di.main.userdetails

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.akhbulatov.archsample.ui.global.Screens
import com.akhbulatov.archsample.ui.main.userdetails.UserDetailsFragment
import com.akhbulatov.archsample.ui.main.userdetails.UserDetailsPresenter
import dagger.Module
import dagger.Provides
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver

@Module
class UserDetailsModule {
    @Provides
    @UserDetailsScope
    fun providePresenter(
        navigator: Navigator,
        screenResolver: ScreenResolver,
        dataManager: DataManager,
        fragment: UserDetailsFragment,
        errorHandler: ErrorHandler
    ): UserDetailsPresenter {
        val screen = screenResolver.getScreen<Screens.UserDetails>(fragment)
        return UserDetailsPresenter(navigator, dataManager, screen.login, errorHandler)
    }
}