package com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.utils.showToast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView {

    @InjectPresenter
    lateinit var presenter: UserDetailsPresenter

    private lateinit var login: String
    private var userDetails: UserDetails? = null

//    @ProvidePresenter
//    fun providePresenter(): UserDetailsPresenter {
//        return UserDetailsPresenter()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val args = arguments ?: throw IllegalArgumentException("Must pass user login argument.")
        login = args.getString(ARGUMENT_USER_LOGIN)
        // TODO
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
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