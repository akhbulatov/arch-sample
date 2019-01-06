package com.akhbulatov.archsample.presentation.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.presentation.global.BaseActivity
import com.akhbulatov.archsample.presentation.global.BaseFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var navigator: Navigator
    private lateinit var viewModel: MainViewModel

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        navigator = SupportAppNavigator(this, R.id.container)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: viewModel.onBackPressed()
}