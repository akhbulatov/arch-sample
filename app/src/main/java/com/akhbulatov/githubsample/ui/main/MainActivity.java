package com.akhbulatov.githubsample.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.models.User;
import com.akhbulatov.githubsample.ui.main.favoritesroot.FavoritesRootActivity;
import com.akhbulatov.githubsample.ui.main.userdetailsroot.UserDetailsRootActivity;
import com.akhbulatov.githubsample.ui.main.users.UsersFragment;

public final class MainActivity extends AppCompatActivity implements UsersFragment.UsersListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        navigateToUsers();
    }

    private void navigateToUsers() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UsersFragment())
                .commit();
    }

    @Override public void onUserClick(@NonNull User user) {
        navigateToUserDetails(user);
    }

    @Override public void onFavoritesClick() {
        navigateToFavorites();
    }

    private void navigateToUserDetails(@NonNull User user) {
        startActivity(UserDetailsRootActivity.createIntent(this, user.login));
    }

    private void navigateToFavorites() {
        startActivity(new Intent(this, FavoritesRootActivity.class));
    }
}
