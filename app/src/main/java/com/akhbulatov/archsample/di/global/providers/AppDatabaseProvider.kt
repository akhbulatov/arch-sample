package com.akhbulatov.archsample.di.global.providers

import android.arch.persistence.room.Room
import android.content.Context
import com.akhbulatov.archsample.data.local.database.AppDatabase
import javax.inject.Inject
import javax.inject.Provider

class AppDatabaseProvider @Inject constructor(
    private val context: Context
) : Provider<AppDatabase> {

    override fun get(): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "archsample_db")
            .build()
}