package com.akhbulatov.archsample.di.global.modules

import dagger.Module
import dagger.Provides
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.GeneratedNavigationFactory
import javax.inject.Singleton

@Module
class NavigationModule {
    private val androidNavigator = AndroidNavigator(GeneratedNavigationFactory())

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = androidNavigator

    @Provides
    @Singleton
    fun provideNavigationContextBinder(): NavigationContextBinder = androidNavigator

    @Provides
    @Singleton
    fun provideScreenResolver(): ScreenResolver = androidNavigator.screenResolver
}