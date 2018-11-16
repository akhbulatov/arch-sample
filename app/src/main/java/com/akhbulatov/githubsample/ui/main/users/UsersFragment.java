package com.akhbulatov.githubsample.ui.main.users;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akhbulatov.githubsample.App;
import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.models.User;

import java.util.List;

public final class UsersFragment extends Fragment implements UsersView {

    private RecyclerView usersRecyclerView;
    private ViewGroup loadingProgressLayout;

    private UsersPresenter presenter;
    private UsersListener usersListener;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        try {
            usersListener = (UsersListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement "
                    + UsersListener.class.getSimpleName());
        }
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = new UsersPresenter(App.getDataManager());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_users, container, false);
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
        usersRecyclerView = view.findViewById(R.id.usersRecyclerView);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(usersRecyclerView.getContext()));
        usersRecyclerView.addItemDecoration(new DividerItemDecoration(
                usersRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL)
        );

        loadingProgressLayout = view.findViewById(R.id.loadingProgressLayout);
    }

    private void setupToolbar(@NonNull View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.users_title);
        toolbar.inflateMenu(R.menu.users);
        toolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.favoritesMenu:
                    presenter.onFavoritesClicked();
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override public void showUsers(@NonNull List<User> users) {
        UsersAdapter adapter = new UsersAdapter(users);
        adapter.setUserClickListener(user -> presenter.onUserClicked(user));
        usersRecyclerView.setAdapter(adapter);
    }

    @Override public void showLoadingProgress(boolean show) {
        loadingProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void navigateToUserDetails(@NonNull User user) {
        usersListener.onUserClick(user);
    }

    @Override public void navigateToFavorites() {
        usersListener.onFavoritesClick();
    }

    public interface UsersListener {
        void onUserClick(@NonNull User user);

        void onFavoritesClick();
    }
}
