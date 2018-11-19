package com.akhbulatov.archsample

import android.app.Application
import com.akhbulatov.archsample.di.global.AppComponent
import com.akhbulatov.archsample.di.global.DaggerAppComponent
import com.arellomobile.mvp.MvpFacade

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        appComponent = buildAppComponent()
    }

    private fun buildAppComponent(): AppComponent =
        DaggerAppComponent.builder()
            .context(this)
            .build()

    companion object {
        lateinit var appComponent: AppComponent
    }
}