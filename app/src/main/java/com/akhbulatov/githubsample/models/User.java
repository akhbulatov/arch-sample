package com.akhbulatov.githubsample.models;

import com.google.gson.annotations.SerializedName;

public final class User {
    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;
}
