package com.akhbulatov.archsample.presentation.global

import android.content.Context
import com.akhbulatov.archsample.domain.global.ResourceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidResourceManager @Inject constructor(private val context: Context) : ResourceManager {
    override fun getString(resId: Int): String = context.getString(resId)
}