package com.akhbulatov.archsample.presentation.mvp.main

import com.akhbulatov.archsample.presentation.mvp.global.BasePresenter
import com.akhbulatov.archsample.presentation.mvp.global.Screens
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter(private val router: Router) : BasePresenter<MainView>(router) {

    override fun onFirstViewAttach() {
        router.newRootChain(Screens.Users)
    }
}