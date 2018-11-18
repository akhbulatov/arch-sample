package com.akhbulatov.githubsample.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.akhbulatov.githubsample.data.local.database.favorites.FavoriteDao
import com.akhbulatov.githubsample.data.local.database.favorites.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}