package com.akhbulatov.archsample.di.global.main.users

import com.akhbulatov.archsample.domain.main.users.UsersInteractor
import com.akhbulatov.archsample.presentation.mvp.global.ErrorHandler
import com.akhbulatov.archsample.presentation.mvp.main.users.UsersPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class UsersModule {
    @Provides
    @UsersScope
    fun providePresenter(router: Router, interactor: UsersInteractor, errorHandler: ErrorHandler) =
        UsersPresenter(router, interactor, errorHandler)
}