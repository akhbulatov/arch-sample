package com.akhbulatov.githubsample.data.local.database.favorites

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAll(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoriteEntity)
}