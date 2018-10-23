package com.akhbulatov.githubsample.data.global;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.models.UserDetails;

import java.util.List;

import retrofit2.Call;

public interface DataManager {
    Call<List<User>> getUsers();

    Call<UserDetails> getUserDetails(@NonNull String login);
}
