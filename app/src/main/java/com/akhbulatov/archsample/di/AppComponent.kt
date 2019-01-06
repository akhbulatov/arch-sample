package com.akhbulatov.archsample.di

import com.akhbulatov.archsample.App
import com.akhbulatov.archsample.di.modules.ActivityBuildersModule
import com.akhbulatov.archsample.di.modules.AppModule
import com.akhbulatov.archsample.di.modules.DataModule
import com.akhbulatov.archsample.di.modules.DatabaseModule
import com.akhbulatov.archsample.di.modules.NavigationModule
import com.akhbulatov.archsample.di.modules.NetworkModule
import com.akhbulatov.archsample.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        DataModule::class,
        NavigationModule::class,
        ViewModelModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}