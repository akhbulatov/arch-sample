package com.akhbulatov.archsample.domain.main.userdetails

import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.domain.repositories.FavoritesRepository
import com.akhbulatov.archsample.domain.repositories.UsersRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserDetailsInteractor @Inject constructor(
    private val usersRepository: UsersRepository,
    private val favoritesRepository: FavoritesRepository,
    private val schedulers: SchedulersProvider
) {

    fun getUserDetails(login: String): Single<UserDetails> =
        usersRepository.getUserDetails(login)
            .observeOn(schedulers.ui())

    fun addUserToFavorites(userDetails: UserDetails): Completable =
        favoritesRepository.addUserToFavorites(userDetails)
            .observeOn(schedulers.ui())
}