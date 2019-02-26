package com.akhbulatov.archsample.ui.main

import com.akhbulatov.archsample.ui.global.BasePresenter
import com.akhbulatov.archsample.ui.global.Screens
import com.arellomobile.mvp.InjectViewState
import me.aartikov.alligator.Navigator

@InjectViewState
class MainPresenter(private val navigator: Navigator) : BasePresenter<MainView>(navigator) {

    override fun onFirstViewAttach() {
        navigator.reset(Screens.Users)
    }
}