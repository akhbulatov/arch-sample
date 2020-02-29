package com.akhbulatov.archsample.di.global.providers

import com.akhbulatov.archsample.data.network.GitHubApi
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject
import javax.inject.Provider

class GitHubApiProvider @Inject constructor(
    private val retrofit: Retrofit
) : Provider<GitHubApi> {

    override fun get(): GitHubApi = retrofit.create()
}