package com.akhbulatov.archsample.di.global.providers

import com.akhbulatov.archsample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor() : Provider<OkHttpClient> {
    override fun get(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.BASIC
                }
            }
        )
        .build()
}