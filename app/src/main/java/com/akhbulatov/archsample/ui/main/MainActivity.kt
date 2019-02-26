package com.akhbulatov.archsample.ui.main

import android.os.Bundle
import com.akhbulatov.archsample.App
import com.akhbulatov.archsample.R
import com.akhbulatov.archsample.ui.global.BaseFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.NavigationContextBinder
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigationContextBinder: NavigationContextBinder

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    private lateinit var navigationContext: NavigationContext

    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment?

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.mainComponentBuilder()
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        navigationContext = NavigationContext.Builder(this)
            .containerId(R.id.container)
            .build()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationContextBinder.bind(navigationContext)
    }

    override fun onPause() {
        navigationContextBinder.unbind()
        super.onPause()
    }

    override fun onBackPressed() = currentFragment?.onBackPressed() ?: presenter.onBackPressed()
}