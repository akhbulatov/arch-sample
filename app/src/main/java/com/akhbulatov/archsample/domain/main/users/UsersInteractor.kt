package com.akhbulatov.archsample.domain.main.users

import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.domain.repositories.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UsersRepository,
    private val schedulers: SchedulersProvider
) {

    fun getUsers(): Single<List<User>> =
        usersRepository.getUsers()
            .observeOn(schedulers.ui())
}