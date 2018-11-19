package com.akhbulatov.archsample.data.global

import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.models.UserDetails
import io.reactivex.Completable
import io.reactivex.Single

interface DataManager {
    fun getUsers(): Single<List<User>>
    fun getUserDetails(login: String): Single<UserDetails>

    fun getFavorites(): Single<List<UserDetails>>
    fun addUserToFavorites(userDetails: UserDetails): Completable
}