package com.akhbulatov.githubsample.ui.main.favoritesroot.favorites;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.models.UserDetails;
import com.akhbulatov.githubsample.ui.global.BaseView;

import java.util.List;

public interface FavoritesView extends BaseView {
    void showFavorites(@NonNull List<UserDetails> favorites);

    void showLoadingProgress(boolean show);

    void showEmptyFavorites();

    void backToUsers();
}
