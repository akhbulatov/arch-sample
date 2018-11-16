package com.akhbulatov.githubsample.ui.main.favoritesroot.favorites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhbulatov.githubsample.App;
import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.models.UserDetails;

import java.util.List;

public final class FavoritesFragment extends Fragment implements FavoritesView {

    private RecyclerView favoritesRecyclerView;
    private ViewGroup loadingProgressLayout;
    private ViewGroup loadingErrorLayout;
    private TextView loadingErrorTextView;

    private FavoritesPresenter presenter;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = new FavoritesPresenter(App.getDataManager());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
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
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.favorites_title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(v -> presenter.onBackClicked());

        favoritesRecyclerView = view.findViewById(R.id.favoritesRecyclerView);
        favoritesRecyclerView.addItemDecoration(new DividerItemDecoration(
                favoritesRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL)
        );

        loadingProgressLayout = view.findViewById(R.id.loadingProgressLayout);
        loadingErrorLayout = view.findViewById(R.id.loadingErrorLayout);
        loadingErrorTextView = view.findViewById(R.id.loadingErrorTextView);
    }

    @Override public void showFavorites(@NonNull List<UserDetails> favorites) {
        FavoritesAdapter adapter = new FavoritesAdapter(favorites);
        favoritesRecyclerView.setAdapter(adapter);
    }

    @Override public void showLoadingProgress(boolean show) {
        loadingProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override public void showEmptyFavorites() {
        loadingErrorTextView.setText(R.string.msg_empty_list);
        loadingErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override public void backToUsers() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
