package com.akhbulatov.githubsample.models;

import com.google.gson.annotations.SerializedName;

public final class User {
    @SerializedName("id")
    public long id;

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;
}
