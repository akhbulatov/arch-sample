package com.akhbulatov.archsample.presentation.ui.main.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.presentation.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class UsersFragment : BaseFragment() {
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UsersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeUiChanges()
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

    private fun observeUiChanges() {
        with(viewModel) {
            users.observe(this@UsersFragment, Observer { showUsers(it!!) })
            loadingProgress.observe(this@UsersFragment, Observer { showLoadingProgress(it!!) })
            loadingError.observe(this@UsersFragment, Observer { showError(it!!) })
        }
    }

    private fun setupToolbar() {
        toolbar.run {
            setTitle(R.string.users_title)
            inflateMenu(R.menu.users)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favoritesMenu -> {
                        viewModel.onFavoritesClicked()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun showUsers(users: List<User>) {
        val adapter = UsersAdapter(users)
        adapter.setOnUserClickListener { viewModel.onUserClicked(it) }
        usersRecyclerView.adapter = adapter
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