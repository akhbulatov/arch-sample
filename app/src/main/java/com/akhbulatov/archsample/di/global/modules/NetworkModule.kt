package com.akhbulatov.archsample.di.global.modules

import com.akhbulatov.archsample.data.network.GitHubApi
import com.akhbulatov.archsample.di.global.providers.GitHubApiProvider
import com.akhbulatov.archsample.di.global.providers.OkHttpClientProvider
import com.akhbulatov.archsample.di.global.providers.RetrofitProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import toothpick.config.Module

class NetworkModule : Module() {
    init {
        bind(OkHttpClient::class.java)
            .toProvider(OkHttpClientProvider::class.java)
            .providesSingletonInScope()

        bind(Retrofit::class.java)
            .toProvider(RetrofitProvider::class.java)
            .providesSingletonInScope()

        bind(GitHubApi::class.java)
            .toProvider(GitHubApiProvider::class.java)
            .providesSingletonInScope()
    }
}