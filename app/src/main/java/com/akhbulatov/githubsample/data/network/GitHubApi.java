package com.akhbulatov.githubsample.data.network;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.models.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{login}")
    Call<UserDetails> getUserDetails(@Path("login") @NonNull String login);
}
