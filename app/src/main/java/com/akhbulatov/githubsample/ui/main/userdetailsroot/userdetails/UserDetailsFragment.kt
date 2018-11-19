package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.githubsample.App
import com.akhbulatov.githubsample.R
import com.akhbulatov.githubsample.models.UserDetails
import com.akhbulatov.githubsample.ui.global.utils.showToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class UserDetailsFragment : Fragment(), UserDetailsView {

    private lateinit var presenter: UserDetailsPresenter
    private lateinit var login: String
    private var userDetails: UserDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments ?: throw IllegalArgumentException("Must pass user login argument.")
        login = args.getString(ARGUMENT_USER_LOGIN)
        presenter = UserDetailsPresenter(App.dataManager, login, App.errorHandler)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun setupToolbar() {
        toolbar.run {
            title = login
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { presenter.onBackClicked() }
            inflateMenu(R.menu.user_details)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.addToFavoritesMenu -> {
                        userDetails?.let { presenter.onAddToFavoritesClicked(it) }
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun showContentLayout(show: Boolean) {
        contentLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showUserDetails(userDetails: UserDetails) {
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

    override fun showLoadingProgress(show: Boolean) {
        loadingProgressLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(error: String) {
        loadingErrorTextView.text = error
        loadingErrorLayout.visibility = View.VISIBLE
    }

    override fun showToFavoritesAdded() {
        showToast(R.string.user_details_added_to_favorites)
    }

    override fun backToUser() {
        activity?.finish()
    }

    companion object {
        private const val ARGUMENT_USER_LOGIN = "user_login"

        fun newInstance(userLogin: String): UserDetailsFragment {
            val args = Bundle().apply { putString(ARGUMENT_USER_LOGIN, userLogin) }
            return UserDetailsFragment().apply { arguments = args }
        }
    }
}
