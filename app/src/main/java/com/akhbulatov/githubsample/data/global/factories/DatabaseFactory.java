package com.akhbulatov.githubsample.data.global.factories;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.local.database.AppDatabase;

public final class DatabaseFactory {

    private static AppDatabase appDatabase;

    private DatabaseFactory() {
        throw new AssertionError("No instance!");
    }

    public static AppDatabase getDatabase(@NonNull Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "githubsample_db"
            ).build();
        }
        return appDatabase;
    }
}
