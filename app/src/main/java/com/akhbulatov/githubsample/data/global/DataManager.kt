package com.akhbulatov.githubsample.data.global

import com.akhbulatov.githubsample.models.User
import com.akhbulatov.githubsample.models.UserDetails

import retrofit2.Call

interface DataManager {
    fun getUsers(): Call<List<User>>
    fun getUserDetails(login: String): Call<UserDetails>

    fun getFavorites(): List<UserDetails>
    fun addUserToFavorites(userDetails: UserDetails)
}