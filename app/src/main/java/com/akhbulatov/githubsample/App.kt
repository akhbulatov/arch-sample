package com.akhbulatov.githubsample

import android.app.Application

import com.akhbulatov.githubsample.data.global.DataManager
import com.akhbulatov.githubsample.data.global.DataManagerImpl
import com.akhbulatov.githubsample.data.global.factories.DatabaseFactory
import com.akhbulatov.githubsample.data.global.factories.NetworkFactory
import com.akhbulatov.githubsample.data.global.factories.PrefsFactory
import com.akhbulatov.githubsample.data.local.database.favorites.FavoritesDatabaseMapper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDataManager()
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
    }
}