package com.akhbulatov.archsample.presentation.ui.main.users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.databinding.FragmentUsersBinding
import com.akhbulatov.archsample.domain.models.User
import com.akhbulatov.archsample.presentation.global.BaseFragment

class UsersFragment : BaseFragment() {
    private lateinit var viewModel: UsersViewModel
    private lateinit var binding: FragmentUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UsersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel
        observeUiChanges()
    }

    private fun initViews() {
        setupToolbar()
        binding.usersRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupToolbar() {
        binding.usersToolbar.toolbar.run {
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

    private fun observeUiChanges() {
        viewModel.users.observe(this@UsersFragment, Observer { showUsers(it!!) })
    }

    private fun showUsers(users: List<User>) {
        val adapter = UsersAdapter(users)
        adapter.setOnUserClickListener { viewModel.onUserClicked(it) }
        binding.usersRecyclerView.adapter = adapter
    }

    override fun onBackPressed() = viewModel.onBackPressed()
}