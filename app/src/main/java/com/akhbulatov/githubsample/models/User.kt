package com.akhbulatov.githubsample.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)