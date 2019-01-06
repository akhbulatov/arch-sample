package com.akhbulatov.archsample.presentation.ui.main

import com.akhbulatov.archsample.presentation.global.BaseViewModel
import com.akhbulatov.archsample.presentation.global.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(router: Router) : BaseViewModel(router) {
    init {
        router.newRootChain(Screens.Users)
    }
}