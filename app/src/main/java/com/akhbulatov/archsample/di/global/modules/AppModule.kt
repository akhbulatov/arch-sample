package com.akhbulatov.archsample.di.global.modules

import com.akhbulatov.archsample.domain.global.ResourceManager
import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.presentation.mvp.global.AndroidResourceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideResourceManager(resourceManager: AndroidResourceManager): ResourceManager

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        fun provideSchedulersProvider() = SchedulersProvider()
    }
}