package com.akhbulatov.archsample.ui.global.utils

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.showToast(@StringRes resId: Int) {
    context?.let { Toast.makeText(it, resId, Toast.LENGTH_SHORT).show() }
}