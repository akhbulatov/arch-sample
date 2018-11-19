package com.akhbulatov.archsample.di.global.modules

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.data.global.DataManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun provideDataManager(dataManager: DataManagerImpl): DataManager
}