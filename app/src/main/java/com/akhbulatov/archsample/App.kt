package com.akhbulatov.archsample

import android.app.Application
import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.data.global.DataManagerImpl
import com.akhbulatov.archsample.data.global.factories.DatabaseFactory
import com.akhbulatov.archsample.data.global.factories.NetworkFactory
import com.akhbulatov.archsample.data.global.factories.PrefsFactory
import com.akhbulatov.archsample.data.local.database.favorites.FavoritesDatabaseMapper
import com.akhbulatov.archsample.ui.global.ErrorHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDataManager()
        errorHandler = ErrorHandler(this)
    }

    private fun initDataManager() {
        dataManager = DataManagerImpl(
            NetworkFactory.api,
            DatabaseFactory.getDatabase(this),
            PrefsFactory.getPrefsHelper(this),
            FavoritesDatabaseMapper()
        )
    }

    companion object {
        lateinit var dataManager: DataManager
        lateinit var errorHandler: ErrorHandler
    }
}