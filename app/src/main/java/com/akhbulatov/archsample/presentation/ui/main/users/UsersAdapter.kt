package com.akhbulatov.archsample.presentation.ui.main.users

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.databinding.ItemUserBinding
import com.akhbulatov.archsample.domain.models.User

private typealias OnUserClickListener = ((User) -> Unit)

class UsersAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var clickListener: OnUserClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            inflater, R.layout.item_user, parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(users[position], clickListener)

    override fun getItemCount(): Int = users.size

    fun setOnUserClickListener(listener: OnUserClickListener?) {
        clickListener = listener
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, clickListener: OnUserClickListener?) {
            binding.run {
                this.user = user
                executePendingBindings()
            }
            itemView.setOnClickListener { clickListener?.invoke(user) }
        }
    }
}