package com.akhbulatov.githubsample.ui.main.users;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhbulatov.githubsample.R;
import com.akhbulatov.githubsample.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public final class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    @NonNull private List<User> users;
    @Nullable private UserClickListener listener;

    UsersAdapter(@NonNull List<User> users) {
        this.users = users;
        listener = null;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder((itemView));
    }

    @Override public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(users.get(position), listener);
    }

    @Override public int getItemCount() {
        return users.size();
    }

    void setUserClickListener(@Nullable UserClickListener listener) {
        this.listener = listener;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImageView;
        private TextView loginTextView;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            loginTextView = itemView.findViewById(R.id.loginTextView);
        }

        void bind(@NonNull User user, @Nullable UserClickListener listener) {
            Picasso.get()
                    .load(user.avatarUrl)
                    .placeholder(R.drawable.ic_account_circle_black)
                    .into(avatarImageView);
            loginTextView.setText(user.login);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUserClick(user);
                }
            });
        }
    }

    public interface UserClickListener {
        void onUserClick(@NonNull User user);
    }
}
