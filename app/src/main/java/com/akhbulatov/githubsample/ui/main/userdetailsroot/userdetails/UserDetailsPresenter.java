package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.global.DataManager;
import com.akhbulatov.githubsample.models.UserDetails;
import com.akhbulatov.githubsample.ui.global.BasePresenterImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class UserDetailsPresenter extends BasePresenterImpl<UserDetailsView> {

    @NonNull private final DataManager dataManager;
    @NonNull private final String login;

    UserDetailsPresenter(@NonNull DataManager dataManager, @NonNull String login) {
        this.dataManager = dataManager;
        this.login = login;
    }

    @Override public void attachView(@NonNull UserDetailsView view) {
        super.attachView(view);
        loadUserDetails();
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

    void onBackClicked() {
        if (getView() != null) {
            getView().backToUser();
        }
    }
}
