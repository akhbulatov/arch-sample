package com.akhbulatov.archsample.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.akhbulatov.archsample.data.global.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "archsample_db")
            .build()
}