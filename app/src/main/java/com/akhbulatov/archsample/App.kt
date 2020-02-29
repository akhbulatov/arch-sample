package com.akhbulatov.archsample

import android.app.Application
import com.akhbulatov.archsample.di.global.DI
import com.akhbulatov.archsample.di.global.modules.AppModule
import com.akhbulatov.archsample.di.global.modules.DataModule
import com.akhbulatov.archsample.di.global.modules.DatabaseModule
import com.akhbulatov.archsample.di.global.modules.NetworkModule
import com.arellomobile.mvp.MvpFacade
import toothpick.Toothpick
import toothpick.smoothie.module.SmoothieApplicationModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        initToothpick()
    }

    private fun initToothpick() {
        Toothpick.openScope(DI.APP_SCOPE)
            .installModules(
                SmoothieApplicationModule(this),
                AppModule(applicationContext),
                NetworkModule(),
                DatabaseModule(),
                DataModule()
            )
    }
}