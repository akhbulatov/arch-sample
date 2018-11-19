package com.akhbulatov.archsample.presentation.ui.main.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.models.User
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

private typealias OnUserClickListener = ((User) -> Unit)

class UsersAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var clickListener: OnUserClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(users[position], clickListener)

    override fun getItemCount(): Int = users.size

    fun setOnUserClickListener(listener: OnUserClickListener?) {
        clickListener = listener
    }

    class UserViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: User, clickListener: OnUserClickListener?) {
            Picasso.get()
                .load(user.avatarUrl)
                .placeholder(R.drawable.ic_account_circle_black)
                .into(avatarImageView)
            loginTextView.text = user.login

            itemView.setOnClickListener { clickListener?.invoke(user) }
        }
    }
}