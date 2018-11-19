package com.akhbulatov.archsample.ui.global

import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment() {
    abstract fun onBackPressed()
}