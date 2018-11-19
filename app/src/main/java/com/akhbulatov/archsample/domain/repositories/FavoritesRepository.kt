package com.akhbulatov.archsample.domain.repositories

import com.akhbulatov.archsample.domain.models.UserDetails
import io.reactivex.Completable
import io.reactivex.Single

interface FavoritesRepository {
    fun getFavorites(): Single<List<UserDetails>>
    fun addUserToFavorites(userDetails: UserDetails): Completable
}