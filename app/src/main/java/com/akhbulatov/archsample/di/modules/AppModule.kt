package com.akhbulatov.archsample.di.modules

import android.content.Context
import com.akhbulatov.archsample.App
import com.akhbulatov.archsample.domain.global.ResourceManager
import com.akhbulatov.archsample.domain.global.SchedulersProvider
import com.akhbulatov.archsample.presentation.global.AndroidResourceManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindResourceManager(resourceManager: AndroidResourceManager): ResourceManager

    @Module
    companion object {
        @JvmStatic
        @Provides
        @Singleton
        fun provideContext(app: App): Context = app.applicationContext

        @Provides
        @JvmStatic
        @Singleton
        fun provideSchedulersProvider() = SchedulersProvider()
    }
}