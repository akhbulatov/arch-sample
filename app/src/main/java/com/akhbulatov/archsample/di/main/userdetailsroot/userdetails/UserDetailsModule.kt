package com.akhbulatov.archsample.di.main.userdetailsroot.userdetails

import com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails.UserDetailsPresenter
import toothpick.config.Module

class UserDetailsModule : Module() {
    init {
        bind(UserDetailsPresenter::class.java)
    }
}