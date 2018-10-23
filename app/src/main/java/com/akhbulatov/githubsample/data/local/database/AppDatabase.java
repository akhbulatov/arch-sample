package com.akhbulatov.githubsample.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.akhbulatov.githubsample.data.local.database.user.UserDao;
import com.akhbulatov.githubsample.data.local.database.user.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    abstract UserDao userDao();
}
