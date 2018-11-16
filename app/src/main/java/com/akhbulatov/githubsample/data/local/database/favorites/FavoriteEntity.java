package com.akhbulatov.githubsample.data.local.database.favorites;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorites")
public final class FavoriteEntity {
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "login")
    public String login;

    @ColumnInfo(name = "avatar_url")
    public String avatarUrl;

    @ColumnInfo(name = "name")
    public String name;
}
