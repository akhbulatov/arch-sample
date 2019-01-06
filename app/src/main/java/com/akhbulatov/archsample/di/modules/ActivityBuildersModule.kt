package com.akhbulatov.archsample.di.modules

import com.akhbulatov.archsample.di.scope.ActivityScope
import com.akhbulatov.archsample.presentation.ui.main.MainActivity
import com.akhbulatov.archsample.presentation.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}