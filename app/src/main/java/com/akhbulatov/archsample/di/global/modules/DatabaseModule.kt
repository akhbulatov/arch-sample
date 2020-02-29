package com.akhbulatov.archsample.di.global.modules

import com.akhbulatov.archsample.data.local.database.AppDatabase
import com.akhbulatov.archsample.di.global.providers.AppDatabaseProvider
import toothpick.config.Module

class DatabaseModule : Module() {
    init {
        bind(AppDatabase::class.java)
            .toProvider(AppDatabaseProvider::class.java)
            .providesSingletonInScope()
    }
}