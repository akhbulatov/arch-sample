package com.akhbulatov.archsample.domain.main.favorites

import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.domain.repositories.FavoritesRepository
import io.reactivex.Single
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val schedulers: SchedulersProvider
) {

    fun getFavorites(): Single<List<UserDetails>> =
        favoritesRepository.getFavorites()
            .observeOn(schedulers.ui())
}