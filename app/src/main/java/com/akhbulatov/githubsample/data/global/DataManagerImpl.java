package com.akhbulatov.githubsample.data.global;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.local.database.AppDatabase;
import com.akhbulatov.githubsample.data.local.database.favorites.FavoriteEntity;
import com.akhbulatov.githubsample.data.local.prefs.PrefsHelper;
import com.akhbulatov.githubsample.data.network.GitHubApi;
import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.models.UserDetails;

import java.util.ArrayList;
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

    @NonNull
    @Override
    public List<UserDetails> getFavorites() {
        List<FavoriteEntity> favoriteEntities = database.favoriteDao().getAll();
        return Mapper.map(favoriteEntities);
    }

    @Override public void addUserToFavorites(@NonNull UserDetails userDetails) {
        database.favoriteDao().insert(Mapper.map(userDetails));
    }

    /**
     * Map models from <b>data</b> layer to <b>presentation</b> and vice versa.
     */
    private static class Mapper {

        @NonNull static List<UserDetails> map(@NonNull List<FavoriteEntity> entities) {
            List<UserDetails> result = new ArrayList<>(entities.size());
            for (FavoriteEntity entity : entities) {
                UserDetails userDetails = new UserDetails();
                userDetails.login = entity.login;
                userDetails.avatarUrl = entity.avatarUrl;
                userDetails.name = entity.name;

                result.add(userDetails);
            }
            return result;
        }

        @NonNull static FavoriteEntity map(@NonNull UserDetails userDetails) {
            FavoriteEntity entity = new FavoriteEntity();
            entity.login = userDetails.login;
            entity.avatarUrl = userDetails.avatarUrl;
            entity.name = userDetails.name;
            return entity;
        }
    }
}
