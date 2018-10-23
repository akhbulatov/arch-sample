package com.akhbulatov.githubsample.data.local.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public final class PrefsHelper {

    @NonNull private final SharedPreferences sharedPrefs;

    public PrefsHelper(@NonNull SharedPreferences sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
    }
}
