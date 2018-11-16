package com.akhbulatov.githubsample.ui.main.users;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.ui.global.BaseView;

import java.util.List;

public interface UsersView extends BaseView {
    void showUsers(@NonNull List<User> users);

    void showLoadingProgress(boolean show);

    void navigateToUserDetails(@NonNull User user);

    void navigateToFavorites();
}
