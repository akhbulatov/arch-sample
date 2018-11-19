package com.akhbulatov.archsample.data.global.network

import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.domain.models.UserDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun getUsers(): Single<List<User>>

    @GET("users/{login}")
    fun getUserDetails(@Path("login") login: String): Single<UserDetails>
}