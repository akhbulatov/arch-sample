package com.akhbulatov.archsample.presentation.ui.main.userdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.domain.models.UserDetails
import com.akhbulatov.archsample.presentation.global.BaseFragment
import com.akhbulatov.archsample.presentation.utils.showToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class UserDetailsFragment : BaseFragment() {
    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var login: String

    private var userDetails: UserDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val args = arguments ?: throw IllegalArgumentException("Must pass user login argument.")
        login = args.getString(ARGUMENT_LOGIN, "akhbulatov")
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UserDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        viewModel.setLogin(login)
        observeUiChanges()
    }

    private fun setupToolbar() {
        toolbar.run {
            title = login
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { viewModel.onBackPressed() }
            inflateMenu(R.menu.user_details)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.addToFavoritesMenu -> {
                        userDetails?.let { viewModel.onAddToFavoritesClicked(it) }
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun observeUiChanges() {
        with(viewModel) {
            userDetails.observe(this@UserDetailsFragment, Observer { showUserDetails(it!!) })
            showContentLayout.observe(this@UserDetailsFragment, Observer { showContentLayout(it!!) })
            loadingProgress.observe(this@UserDetailsFragment, Observer { showLoadingProgress(it!!) })
            loadingError.observe(this@UserDetailsFragment, Observer { showError(it!!) })
            addedToFavorites.observe(this@UserDetailsFragment, Observer { showToFavoritesAdded() })
        }
    }

    private fun showContentLayout(show: Boolean) {
        contentLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showUserDetails(userDetails: UserDetails) {
        this.userDetails = userDetails

        Picasso.get()
            .load(userDetails.avatarUrl)
            .placeholder(R.drawable.img_no_image)
            .into(avatarImageView)
        loginValTextView.text = userDetails.login
        nameValTextView.text = userDetails.name
        locationValTextView.text = userDetails.location
        publicReposValTextView.text = userDetails.publicRepos.toString()
        followersValTextView.text = userDetails.followers.toString()
        followingValTextView.text = userDetails.following.toString()
    }

    private fun showLoadingProgress(show: Boolean) {
        loadingProgressLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(error: String) {
        loadingErrorTextView.text = error
        loadingErrorLayout.visibility = View.VISIBLE
    }

    private fun showToFavoritesAdded() {
        showToast(R.string.user_details_added_to_favorites)
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    companion object {
        private const val ARGUMENT_LOGIN = "login"

        fun newInstance(login: String): UserDetailsFragment {
            val args = Bundle().apply { putString(ARGUMENT_LOGIN, login) }
            return UserDetailsFragment().apply { arguments = args }
        }
    }
}
