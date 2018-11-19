package com.akhbulatov.archsample.presentation.ui.main.users

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.App
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.presentation.mvp.main.users.UsersPresenter
import com.akhbulatov.archsample.presentation.mvp.main.users.UsersView
import com.akhbulatov.archsample.presentation.ui.global.BaseFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class UsersFragment : BaseFragment(), UsersView {

    @Inject
    @InjectPresenter
    lateinit var presenter: UsersPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.usersComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setupToolbar()
        usersRecyclerView.run {
            layoutManager = LinearLayoutManager(usersRecyclerView.context)
            addItemDecoration(
                DividerItemDecoration(usersRecyclerView.context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun setupToolbar() {
        toolbar.run {
            setTitle(R.string.users_title)
            inflateMenu(R.menu.users)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favoritesMenu -> {
                        presenter.onFavoritesClicked()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun showUsers(users: List<User>) {
        val adapter = UsersAdapter(users)
        adapter.setOnUserClickListener { presenter.onUserClicked(it) }
        usersRecyclerView.adapter = adapter
    }

    override fun showLoadingProgress(show: Boolean) {
        loadingProgressLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(error: String) {
        loadingErrorTextView.text = error
        loadingErrorLayout.visibility = View.VISIBLE
    }

    override fun onBackPressed() = presenter.onBackPressed()
}