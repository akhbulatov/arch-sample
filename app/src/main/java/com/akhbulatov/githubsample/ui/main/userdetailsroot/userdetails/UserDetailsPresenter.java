package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.akhbulatov.githubsample.data.global.DataManager;
import com.akhbulatov.githubsample.models.UserDetails;
import com.akhbulatov.githubsample.ui.global.BasePresenterImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

final class UserDetailsPresenter extends BasePresenterImpl<UserDetailsView> {

    @NonNull private final DataManager dataManager;
    @NonNull private final String login;
    @NonNull private final ExecutorService executor;

    UserDetailsPresenter(@NonNull DataManager dataManager, @NonNull String login) {
        this.dataManager = dataManager;
        this.login = login;
        executor = Executors.newSingleThreadExecutor();
    }

    @Override public void attachView(@NonNull UserDetailsView view) {
        super.attachView(view);
        loadUserDetails();
    }

    @Override public void detachView() {
        executor.shutdown();
        super.detachView();
    }

    private void loadUserDetails() {
        if (getView() != null) {
            getView().showLoadingProgress(true);
            getView().showContentLayout(false);

            Call<UserDetails> userDetailsRequest = dataManager.getUserDetails(login);
            userDetailsRequest.enqueue(new Callback<UserDetails>() {

                @Override public void onResponse(
                        @NonNull Call<UserDetails> call,
                        @NonNull Response<UserDetails> response
                ) {
                    if (getView() != null) {
                        getView().showLoadingProgress(false);
                        getView().showContentLayout(true);

                        if (response.isSuccessful()) {
                            getView().showUserDetails(response.body());
                        } else {

                        }
                    }
                }

                @Override public void onFailure(@NonNull Call<UserDetails> call, @NonNull Throwable t) {
                    if (!call.isCanceled() && getView() != null) {
                        getView().showLoadingProgress(false);
                    }
                }
            });

            addRequest(userDetailsRequest);
        }
    }

    void onAddToFavoritesClicked(@Nullable UserDetails userDetails) {
        if (userDetails != null) {
            executor.submit(() -> dataManager.addUserToFavorites(userDetails));

            if (getView() != null) {
                getView().showToFavoritesAdded();
            }
        }
    }

    void onBackClicked() {
        if (getView() != null) {
            getView().backToUser();
        }
    }
}
