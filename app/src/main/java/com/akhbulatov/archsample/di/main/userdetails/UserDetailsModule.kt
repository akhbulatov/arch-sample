package com.akhbulatov.archsample.di.main.userdetails

import com.akhbulatov.archsample.domain.main.userdetails.UserDetailsInteractor
import com.akhbulatov.archsample.presentation.mvp.global.ErrorHandler
import com.akhbulatov.archsample.presentation.mvp.main.userdetails.UserDetailsPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class UserDetailsModule {
    @Provides
    @UserDetailsScope
    fun providePresenter(
        router: Router,
        interactor: UserDetailsInteractor,
        login: String,
        errorHandler: ErrorHandler
    ) = UserDetailsPresenter(router, interactor, login, errorHandler)
}