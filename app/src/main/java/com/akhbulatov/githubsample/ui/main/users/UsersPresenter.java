package com.akhbulatov.githubsample.ui.main.users;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.data.global.DataManager;
import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.ui.global.BasePresenterImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class UsersPresenter extends BasePresenterImpl<UsersView> {

    @NonNull private final DataManager dataManager;

    UsersPresenter(@NonNull DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override public void attachView(@NonNull UsersView view) {
        super.attachView(view);
        loadUsers();
    }

    private void loadUsers() {
        if (getView() != null) {
            getView().showLoadingProgress(true);

            Call<List<User>> usersRequest = dataManager.getUsers();
            usersRequest.enqueue(new Callback<List<User>>() {

                @Override public void onResponse(
                        @NonNull Call<List<User>> call,
                        @NonNull Response<List<User>> response
                ) {
                    if (getView() != null) {
                        getView().showLoadingProgress(false);

                        if (response.isSuccessful()) {
                            getView().showUsers(response.body());
                        } else {

                        }
                    }
                }

                @Override public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                    if (!call.isCanceled() && getView() != null) {
                        getView().showLoadingProgress(false);


                    }
                }
            });

            addRequest(usersRequest);
        }
    }

    void onUserClicked(@NonNull User user) {
        if (getView() != null) {
            getView().navigateToUserDetails(user);
        }
    }

    void onFavoritesClicked() {
        if (getView() != null) {
            getView().navigateToFavorites();
        }
    }
}
