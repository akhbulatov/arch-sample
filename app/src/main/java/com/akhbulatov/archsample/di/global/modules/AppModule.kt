package com.akhbulatov.archsample.di.global.modules

import android.content.Context
import toothpick.config.Module

class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
    }
}