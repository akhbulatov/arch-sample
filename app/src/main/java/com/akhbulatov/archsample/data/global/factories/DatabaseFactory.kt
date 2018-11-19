package com.akhbulatov.archsample.data.global.factories

import android.arch.persistence.room.Room
import android.content.Context
import com.akhbulatov.archsample.data.local.database.AppDatabase

object DatabaseFactory {

    private lateinit var appDatabase: AppDatabase

    fun getDatabase(context: Context): AppDatabase {
        if (!this::appDatabase.isInitialized) {
            appDatabase = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "archsample_db"
            ).build()
        }
        return appDatabase
    }
}