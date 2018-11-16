package com.akhbulatov.githubsample.ui.main.favoritesroot.favorites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.models.UserDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public final class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    @NonNull private List<UserDetails> favorites;

    FavoritesAdapter(@NonNull List<UserDetails> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder((itemView));
    }

    @Override public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(favorites.get(position));
    }

    @Override public int getItemCount() {
        return favorites.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;
        private TextView loginTextView;
        private TextView nameTextView;

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            loginTextView = itemView.findViewById(R.id.loginTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }

        void bind(@NonNull UserDetails favorites) {
            Picasso.get()
                    .load(favorites.avatarUrl)
                    .placeholder(R.drawable.ic_account_circle_black)
                    .into(avatarImageView);
            loginTextView.setText(favorites.login);
            nameTextView.setText(favorites.name);
        }
    }
}
