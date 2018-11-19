package com.akhbulatov.archsample.ui.main.favoritesroot.favorites

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.App
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.models.UserDetails
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class FavoritesFragment : MvpAppCompatFragment(), FavoritesView {

    @InjectPresenter lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter() = FavoritesPresenter(App.dataManager)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
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