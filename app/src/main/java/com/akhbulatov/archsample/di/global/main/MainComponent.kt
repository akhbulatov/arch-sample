package com.akhbulatov.archsample.di.global.main

import com.akhbulatov.archsample.presentation.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }
}