package com.akhbulatov.archsample.presentation.ui.main.favorites

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.databinding.ItemFavoriteBinding
import com.akhbulatov.archsample.domain.models.UserDetails

class FavoritesAdapter(
    private val favorites: List<UserDetails>
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFavoriteBinding>(
            inflater, R.layout.item_favorite, parent, false
        )
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) =
        holder.bind(favorites[position])

    override fun getItemCount(): Int = favorites.size

    class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favorites: UserDetails) {
            binding.run {
                userDetails = favorites
                executePendingBindings()
            }
        }
    }
}