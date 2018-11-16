package com.akhbulatov.githubsample.ui.main.favoritesroot.favorites;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.global.DataManager;
import com.akhbulatov.githubsample.models.UserDetails;
import com.akhbulatov.githubsample.ui.global.BasePresenterImpl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class FavoritesPresenter extends BasePresenterImpl<FavoritesView> {

    @NonNull private final ExecutorService executor;
    @NonNull private final Callable<List<UserDetails>> favoritesCallable;

    FavoritesPresenter(@NonNull DataManager dataManager) {
        executor = Executors.newSingleThreadExecutor();
        favoritesCallable = dataManager::getFavorites;
    }

    @Override public void attachView(@NonNull FavoritesView view) {
        super.attachView(view);
        loadFavorites();
    }

    @Override public void detachView() {
        executor.shutdown();
        super.detachView();
    }

    private void loadFavorites() {
        if (getView() != null) {
            getView().showLoadingProgress(true);

            try {
                List<UserDetails> favorites = executor.submit(favoritesCallable).get();

                if (getView() != null) {
                    getView().showLoadingProgress(false);

                    if (!favorites.isEmpty()) {
                        getView().showFavorites(favorites);
                    } else {
                        getView().showEmptyFavorites();
                    }
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();

                if (getView() != null) {
                    getView().showLoadingProgress(false);
                }
            }
        }
    }

    void onBackClicked() {
        if (getView() != null) {
            getView().backToUsers();
        }
    }
}