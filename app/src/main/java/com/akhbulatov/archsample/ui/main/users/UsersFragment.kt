package com.akhbulatov.archsample.ui.main.users

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.di.global.DI
import com.akhbulatov.archsample.di.main.users.UsersModule
import com.akhbulatov.archsample.models.User
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*
import toothpick.Toothpick

class UsersFragment : MvpAppCompatFragment(), UsersView {

    @InjectPresenter
    lateinit var presenter: UsersPresenter

    private var usersListener: UsersListener? = null

    @ProvidePresenter
    fun providePresenter(): UsersPresenter {
        val scope = Toothpick.openScopes(DI.APP_SCOPE, DI.USERS_SCOPE)
        scope.installModules(UsersModule())
        return scope.getInstance(UsersPresenter::class.java)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            if (it !is UsersListener) {
                throw ClassCastException("$it must implement ${UsersListener::class.java.simpleName})")
            }
            usersListener = it
        }
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

    override fun navigateToUserDetails(user: User) {
        usersListener?.onUserClick(user)
    }

    override fun navigateToFavorites() {
        usersListener?.onFavoritesClick()
    }

    interface UsersListener {
        fun onUserClick(user: User)
        fun onFavoritesClick()
    }
}