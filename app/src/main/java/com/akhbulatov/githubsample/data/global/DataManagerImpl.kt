package com.akhbulatov.githubsample.data.global

import com.akhbulatov.githubsample.data.local.database.AppDatabase
import com.akhbulatov.githubsample.data.local.database.favorites.FavoritesDatabaseMapper
import com.akhbulatov.githubsample.data.local.prefs.PrefsHelper
import com.akhbulatov.githubsample.data.network.GitHubApi
import com.akhbulatov.githubsample.models.User
import com.akhbulatov.githubsample.models.UserDetails
import retrofit2.Call

class DataManagerImpl(
    private val api: GitHubApi,
    private val database: AppDatabase,
    private val prefsHelper: PrefsHelper,
    private val favoritesDatabaseMapper: FavoritesDatabaseMapper
) : DataManager {

    override fun getUsers(): Call<List<User>> = api.getUsers()
    override fun getUserDetails(login: String): Call<UserDetails> = api.getUserDetails(login)

    override fun getFavorites(): List<UserDetails> {
        val favorites = database.favoriteDao().getAll()
        return favoritesDatabaseMapper.mapFrom(favorites)
    }

    override fun addUserToFavorites(userDetails: UserDetails) {
        val favorite = favoritesDatabaseMapper.mapTo(userDetails)
        database.favoriteDao().insert(favorite)
    }
}
