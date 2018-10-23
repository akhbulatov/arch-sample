package com.akhbulatov.githubsample.ui.main.userdetailsroot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails.UserDetailsFragment;

public class UserDetailsRootActivity extends AppCompatActivity {

    private static final String EXTRA_LOGIN = "login";

    public static Intent createIntent(@NonNull Context context, @NonNull String login) {
        Intent intent = new Intent(context, UserDetailsRootActivity.class);
        intent.putExtra(EXTRA_LOGIN, login);
        return intent;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (getIntent().getExtras() == null) {
            throw new IllegalArgumentException("Must pass login extra.");
        }
        String userLogin = getIntent().getStringExtra(EXTRA_LOGIN);
        navigateToUserDetails(userLogin);
    }

    private void navigateToUserDetails(String userLogin) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, UserDetailsFragment.newInstance(userLogin))
                .commit();
    }
}
