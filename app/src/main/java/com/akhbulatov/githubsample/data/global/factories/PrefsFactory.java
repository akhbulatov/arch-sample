package com.akhbulatov.githubsample.data.global.factories;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.local.prefs.PrefsHelper;

public final class PrefsFactory {

    private static PrefsHelper prefsHelper;

    private PrefsFactory() {
        throw new AssertionError("No instance!");
    }

    public static PrefsHelper getPrefsHelper(@NonNull Context context) {
        if (prefsHelper == null) {
            prefsHelper = createPrefsHelper(context);
        }
        return prefsHelper;
    }


    @NonNull private static PrefsHelper createPrefsHelper(@NonNull Context context) {
        return new PrefsHelper(getSharedPrefs(context));
    }

    @NonNull private static SharedPreferences getSharedPrefs(@NonNull Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("githubsample_prefs", Context.MODE_PRIVATE);
    }
}
