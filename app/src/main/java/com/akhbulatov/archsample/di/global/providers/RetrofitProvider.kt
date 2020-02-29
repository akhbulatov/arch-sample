package com.akhbulatov.archsample.di.global.providers

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class RetrofitProvider @Inject constructor(
    private val client: OkHttpClient
) : Provider<Retrofit> {

    override fun get(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        private const val BASE_API_URL = "https://api.github.com/"
    }
}