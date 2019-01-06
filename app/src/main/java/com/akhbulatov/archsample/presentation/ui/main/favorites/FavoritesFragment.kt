package com.akhbulatov.archsample.presentation.ui.main.favorites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.presentation.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class FavoritesFragment : BaseFragment() {
    lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[FavoritesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeUiChanges()
    }

    private fun initViews() {
        toolbar.run {
            setTitle(R.string.favorites_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { viewModel.onBackPressed() }
        }

        favoritesRecyclerView.addItemDecoration(
            DividerItemDecoration(favoritesRecyclerView.context, DividerItemDecoration.VERTICAL)
        )
    }

    private fun observeUiChanges() {
        with(viewModel) {
            favorites.observe(this@FavoritesFragment, Observer { showFavorites(it!!) })
            loadingProgress.observe(this@FavoritesFragment, Observer { showLoadingProgress(it!!) })
            loadingError.observe(this@FavoritesFragment, Observer { showError(it!!) })
        }
    }

    private fun showFavorites(favorites: List<UserDetails>) {
        val adapter = FavoritesAdapter(favorites)
        favoritesRecyclerView.adapter = adapter
    }

    private fun showLoadingProgress(show: Boolean) {
        loadingProgressLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        loadingErrorTextView.text = error
        loadingErrorLayout.visibility = View.VISIBLE
    }

    override fun onBackPressed() = viewModel.onBackPressed()
}