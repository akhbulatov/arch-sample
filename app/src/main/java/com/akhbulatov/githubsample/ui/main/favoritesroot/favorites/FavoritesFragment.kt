package com.akhbulatov.githubsample.ui.main.favoritesroot.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.githubsample.App
import com.akhbulatov.githubsample.R
import com.akhbulatov.githubsample.models.UserDetails
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class FavoritesFragment : Fragment(), FavoritesView {

    private lateinit var presenter: FavoritesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = FavoritesPresenter(App.dataManager)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun initViews() {
        toolbar.run {
            setTitle(R.string.favorites_title)
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { presenter.onBackClicked() }
        }

        favoritesRecyclerView.addItemDecoration(
            DividerItemDecoration(favoritesRecyclerView.context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun showFavorites(favorites: List<UserDetails>) {
        val adapter = FavoritesAdapter(favorites)
        favoritesRecyclerView.adapter = adapter
    }

    override fun showLoadingProgress(show: Boolean) {
        loadingProgressLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showEmptyFavorites() {
        loadingErrorTextView.setText(R.string.msg_empty_list)
        loadingErrorLayout.visibility = View.VISIBLE
    }

    override fun backToUsers() {
        activity?.finish()
    }
}