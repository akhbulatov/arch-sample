package com.akhbulatov.archsample.data.users

import com.akhbulatov.archsample.data.global.network.GitHubApi
import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.domain.repositories.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: GitHubApi,
    private val schedulers: SchedulersProvider
) : UsersRepository {

    override fun getUsers(): Single<List<User>> =
        api.getUsers()
            .subscribeOn(schedulers.io())

    override fun getUserDetails(login: String): Single<UserDetails> =
        api.getUserDetails(login)
            .subscribeOn(schedulers.io())
}