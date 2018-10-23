package com.akhbulatov.githubsample.data.global;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.local.database.AppDatabase;
import com.akhbulatov.githubsample.data.local.prefs.PrefsHelper;
import com.akhbulatov.githubsample.data.network.GitHubApi;
import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.models.UserDetails;

import java.util.List;

import retrofit2.Call;

public final class DataManagerImpl implements DataManager {

    @NonNull private final GitHubApi api;
    @NonNull private final AppDatabase database;
    @NonNull private final PrefsHelper prefsHelper;

    public DataManagerImpl(
            @NonNull GitHubApi api,
            @NonNull AppDatabase database,
            @NonNull PrefsHelper prefsHelper
    ) {
        this.api = api;
        this.database = database;
        this.prefsHelper = prefsHelper;
    }

    @Override public Call<List<User>> getUsers() {
        return api.getUsers();
    }

    @Override public Call<UserDetails> getUserDetails(@NonNull String login) {
        return api.getUserDetails(login);
    }
}
