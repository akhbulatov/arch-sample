package com.akhbulatov.githubsample.data.local.database.favorites;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    List<FavoriteEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(@NonNull FavoriteEntity entity);
}
