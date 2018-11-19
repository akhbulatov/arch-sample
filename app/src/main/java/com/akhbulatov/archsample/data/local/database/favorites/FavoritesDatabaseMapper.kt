package com.akhbulatov.archsample.data.local.database.favorites

import com.akhbulatov.archsample.models.UserDetails

class FavoritesDatabaseMapper {
    fun mapFrom(entities: List<FavoriteEntity>): List<UserDetails> =
        entities.map {
            UserDetails(
                it.id,
                it.login,
                it.avatarUrl,
                it.name,
                it.location,
                it.publicRepos,
                it.followers,
                it.following
            )
        }

    fun mapTo(entity: UserDetails): FavoriteEntity =
        FavoriteEntity().apply {
            id = entity.id
            login = entity.login
            avatarUrl = entity.avatarUrl
            name = entity.name
            location = entity.location
            publicRepos = entity.publicRepos
            followers = entity.followers
            following = entity.following
        }
}