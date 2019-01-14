package com.akhbulatov.archsample.presentation.ui.main.userdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.databinding.FragmentUserDetailsBinding
import com.akhbulatov.archsample.presentation.global.BaseFragment
import com.akhbulatov.archsample.presentation.utils.showToast

class UserDetailsFragment : BaseFragment() {
    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var login: String
    private lateinit var binding: FragmentUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireNotNull(arguments)
        login = args.getString(ARGUMENT_LOGIN, "akhbulatov")
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UserDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        viewModel.setLogin(login)
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel
        observeUiChanges()
    }

    private fun setupToolbar() {
        binding.userDetailsToolbar.toolbar.run {
            title = login
            setNavigationIcon(R.drawable.ic_arrow_back_white)
            setNavigationOnClickListener { viewModel.onBackPressed() }
            inflateMenu(R.menu.user_details)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.addToFavoritesMenu -> {
                        viewModel.userDetails.value?.let { viewModel.onAddToFavoritesClicked(it) }
                        true
                    }

                    else -> false
                }
            }
        }
    }

    private fun observeUiChanges() {
        viewModel.addedToFavorites.observe(this@UserDetailsFragment, Observer { showToFavoritesAdded() })
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
