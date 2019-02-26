package com.akhbulatov.archsample.ui.main.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.App
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BaseFragment
import com.akhbulatov.archsample.ui.global.Screens
import com.akhbulatov.archsample.ui.global.utils.showToast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.loading_progress.*
import kotlinx.android.synthetic.main.toolbar.*
import me.aartikov.alligator.annotations.RegisterScreen
import javax.inject.Inject

@RegisterScreen(Screens.UserDetails::class)
class UserDetailsFragment : BaseFragment(), UserDetailsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: UserDetailsPresenter

    private var userDetails: UserDetails? = null

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.userDetailsComponentBuilder()
            .fragment(this)
            .build()
            .inject(this)
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
            title = presenter.login
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { presenter.onBackPressed() }
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

    override fun onBackPressed() = presenter.onBackPressed()
}
