package com.akhbulatov.archsample.presentation.ui.main.favorites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.databinding.FragmentFavoritesBinding
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.presentation.global.BaseFragment

class FavoritesFragment : BaseFragment() {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[FavoritesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel
        observeUiChanges()
    }

    private fun initViews() {
        binding.favoritesToolbar.toolbar.run {
            setTitle(R.string.favorites_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { viewModel.onBackPressed() }
        }
        binding.favoritesRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeUiChanges() {
        viewModel.favorites.observe(this@FavoritesFragment, Observer { showFavorites(it!!) })
    }

    private fun showFavorites(favorites: List<UserDetails>) {
        val adapter = FavoritesAdapter(favorites)
        binding.favoritesRecyclerView.adapter = adapter
    }

    override fun onBackPressed() = viewModel.onBackPressed()
}