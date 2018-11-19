package com.akhbulatov.archsample.domain.repositories

import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.domain.models.UserDetails
import io.reactivex.Single

interface UsersRepository {
    fun getUsers(): Single<List<User>>
    fun getUserDetails(login: String): Single<UserDetails>
}