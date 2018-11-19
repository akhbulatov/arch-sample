package com.akhbulatov.archsample.data.global.factories

import android.content.Context
import android.content.SharedPreferences
import com.akhbulatov.archsample.data.local.prefs.PrefsHelper

object PrefsFactory {

    private lateinit var prefsHelper: PrefsHelper

    fun getPrefsHelper(context: Context): PrefsHelper {
        if (!this::prefsHelper.isInitialized) {
            prefsHelper = PrefsHelper(getSharedPrefs(context))
        }
        return prefsHelper
    }

    private fun getSharedPrefs(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences("archsample_prefs", Context.MODE_PRIVATE)
}