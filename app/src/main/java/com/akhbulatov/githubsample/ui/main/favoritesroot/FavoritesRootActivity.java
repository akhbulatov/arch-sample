package com.akhbulatov.githubsample.ui.main.favoritesroot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.ui.main.favoritesroot.favorites.FavoritesFragment;

public final class FavoritesRootActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        navigateToFavorites();
    }

    private void navigateToFavorites() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FavoritesFragment())
                .commit();
    }
}
