package com.akhbulatov.githubsample.models;

import com.google.gson.annotations.SerializedName;

public final class UserDetails {
    @SerializedName("id")
    public long id;

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("name")
    public String name;

    @SerializedName("location")
    public String location;

    @SerializedName("public_repos")
    public int publicRepos;

    @SerializedName("followers")
    public int followers;

    @SerializedName("following")
    public int following;
}
