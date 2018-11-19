package com.akhbulatov.archsample.data.favorites

import com.akhbulatov.archsample.data.global.local.database.AppDatabase
import com.akhbulatov.archsample.data.global.local.database.favorites.FavoritesDatabaseMapper
import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.domain.repositories.FavoritesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val schedulers: SchedulersProvider,
    private val favoritesDatabaseMapper: FavoritesDatabaseMapper
) : FavoritesRepository {

    override fun getFavorites(): Single<List<UserDetails>> =
        appDatabase.favoriteDao().getAll()
            .map { favoritesDatabaseMapper.mapFrom(it) }
            .subscribeOn(schedulers.io())

    override fun addUserToFavorites(userDetails: UserDetails): Completable =
        Completable.fromAction {
            val favorite = favoritesDatabaseMapper.mapTo(userDetails)
            appDatabase.favoriteDao().insert(favorite)
        }
            .subscribeOn(schedulers.io())
}