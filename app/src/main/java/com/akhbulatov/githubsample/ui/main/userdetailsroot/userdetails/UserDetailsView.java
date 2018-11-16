package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.models.UserDetails;
import com.akhbulatov.githubsample.ui.global.BaseView;

interface UserDetailsView extends BaseView {
    void showContentLayout(boolean show);

    void showUserDetails(@NonNull UserDetails userDetails);

    void showLoadingProgress(boolean show);

    void showToFavoritesAdded();

    void backToUser();
}
