package com.akhbulatov.archsample.presentation.global

import android.arch.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun onBackPressed()
}