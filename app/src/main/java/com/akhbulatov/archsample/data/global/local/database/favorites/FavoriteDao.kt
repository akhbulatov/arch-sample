package com.akhbulatov.archsample.data.global.local.database.favorites

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAll(): Single<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoriteEntity)
}