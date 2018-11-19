package com.akhbulatov.archsample.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.akhbulatov.archsample.data.local.database.favorites.FavoriteDao
import com.akhbulatov.archsample.data.local.database.favorites.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}