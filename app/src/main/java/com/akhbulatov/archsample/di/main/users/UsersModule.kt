package com.akhbulatov.archsample.di.main.users

import com.akhbulatov.archsample.ui.main.users.UsersPresenter
import toothpick.config.Module

class UsersModule : Module() {
    init {
        bind(UsersPresenter::class.java)
    }
}