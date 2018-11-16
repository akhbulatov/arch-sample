package com.akhbulatov.githubsample.ui.global.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public final class CommonUtils {

    private CommonUtils() {
        throw new AssertionError("No instance!");
    }

    public static void showToast(Context context, @StringRes int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
