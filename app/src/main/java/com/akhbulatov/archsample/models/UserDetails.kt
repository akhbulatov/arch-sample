package com.akhbulatov.archsample.models

import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("location") val location: String,
    @SerializedName("public_repos") val publicRepos: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int
)