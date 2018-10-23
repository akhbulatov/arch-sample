package com.akhbulatov.githubsample.data.local.database.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "login")
    String login;

    @ColumnInfo(name = "avatar_url")
    String avatarUrl;
}
