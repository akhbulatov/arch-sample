package com.akhbulatov.archsample.data.global

import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.models.UserDetails

import retrofit2.Call

interface DataManager {
    fun getUsers(): Call<List<User>>
    fun getUserDetails(login: String): Call<UserDetails>

    fun getFavorites(): List<UserDetails>
    fun addUserToFavorites(userDetails: UserDetails)
}