package com.akhbulatov.archsample.data.global.local.database.favorites

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "favorites")
class FavoriteEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "login")
    lateinit var login: String

    @ColumnInfo(name = "avatar_url")
    lateinit var avatarUrl: String

    @ColumnInfo(name = "name")
    lateinit var name: String

    @ColumnInfo(name = "location")
    lateinit var location: String

    @ColumnInfo(name = "public_repos")
    var publicRepos: Int = 0

    @ColumnInfo(name = "followers")
    var followers: Int = 0

    @ColumnInfo(name = "following")
    var following: Int = 0
}