package com.akhbulatov.archsample.data.global

import com.akhbulatov.archsample.data.local.database.AppDatabase
import com.akhbulatov.archsample.data.local.database.favorites.FavoritesDatabaseMapper
import com.akhbulatov.archsample.data.local.prefs.PrefsHelper
import com.akhbulatov.archsample.data.network.GitHubApi
import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.models.UserDetails
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
    private val api: GitHubApi,
    private val database: AppDatabase,
    private val prefsHelper: PrefsHelper,
    private val favoritesDatabaseMapper: FavoritesDatabaseMapper
) : DataManager {

    override fun getUsers(): Single<List<User>> =
        api.getUsers()
            .subscribeOn(Schedulers.io())

    override fun getUserDetails(login: String): Single<UserDetails> =
        api.getUserDetails(login)
            .subscribeOn(Schedulers.io())

    override fun getFavorites(): Single<List<UserDetails>> =
        database.favoriteDao().getAll()
            .map { favoritesDatabaseMapper.mapFrom(it) }
            .subscribeOn(Schedulers.io())

    override fun addUserToFavorites(userDetails: UserDetails): Completable =
        Completable.fromAction {
            val favorite = favoritesDatabaseMapper.mapTo(userDetails)
            database.favoriteDao().insert(favorite)
        }
            .subscribeOn(Schedulers.io())
}
