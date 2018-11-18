package com.akhbulatov.githubsample.data.network

import com.akhbulatov.githubsample.models.User
import com.akhbulatov.githubsample.models.UserDetails

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{login}")
    fun getUserDetails(@Path("login") login: String): Call<UserDetails>
}