package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhbulatov.githubsample.App;
import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.models.UserDetails;
import com.akhbulatov.githubsample.ui.global.utils.CommonUtils;
import com.squareup.picasso.Picasso;

public final class UserDetailsFragment extends Fragment implements UserDetailsView {

    private static final String ARGUMENT_USER_LOGIN = "user_login";

    private ViewGroup contentLayout;
    private ImageView avatarImageView;
    private TextView loginValTextView;
    private TextView nameValTextView;
    private TextView locationValTextView;
    private TextView publicReposValTextView;
    private TextView followersValTextView;
    private TextView followingValTextView;
    private ViewGroup loadingProgressLayout;

    private UserDetailsPresenter presenter;
    private String login;
    @Nullable private UserDetails userDetails;

    public static UserDetailsFragment newInstance(String userLogin) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_USER_LOGIN, userLogin);

        UserDetailsFragment fragment = new UserDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args == null) {
            throw new IllegalArgumentException("Must pass user login argument.");
        }
        login = args.getString(ARGUMENT_USER_LOGIN);
        presenter = new UserDetailsPresenter(App.getDataManager(), login);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        presenter.attachView(this);
    }

    @Override public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    private void initViews(@NonNull View view) {
        setupToolbar(view);
        contentLayout = view.findViewById(R.id.contentLayout);
        avatarImageView = view.findViewById(R.id.avatarImageView);
        loginValTextView = view.findViewById(R.id.loginValTextView);
        nameValTextView = view.findViewById(R.id.nameValTextView);
        locationValTextView = view.findViewById(R.id.locationValTextView);
        publicReposValTextView = view.findViewById(R.id.publicReposValTextView);
        followersValTextView = view.findViewById(R.id.followersValTextView);
        followingValTextView = view.findViewById(R.id.followingValTextView);
        loadingProgressLayout = view.findViewById(R.id.loadingProgressLayout);
    }

    private void setupToolbar(@NonNull View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(login);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(v -> presenter.onBackClicked());
        toolbar.inflateMenu(R.menu.user_details);
        toolbar.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.addToFavoritesMenu: {
                            presenter.onAddToFavoritesClicked(userDetails);
                            return true;
                        }
                        default:
                            return false;
                    }
                }
        );
    }

    @Override public void showContentLayout(boolean show) {
        contentLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void showUserDetails(@NonNull UserDetails userDetails) {
        this.userDetails = userDetails;

        Picasso.get()
                .load(userDetails.avatarUrl)
                .placeholder(R.drawable.img_no_image)
                .into(avatarImageView);
        loginValTextView.setText(userDetails.login);
        nameValTextView.setText(userDetails.name);
        locationValTextView.setText(userDetails.location);
        publicReposValTextView.setText(String.valueOf(userDetails.publicRepos));
        followersValTextView.setText(String.valueOf(userDetails.followers));
        followingValTextView.setText(String.valueOf(userDetails.following));
    }

    @Override public void showLoadingProgress(boolean show) {
        loadingProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void showToFavoritesAdded() {
        CommonUtils.showToast(getContext(), R.string.user_details_added_to_favorites);
    }

    @Override public void backToUser() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
