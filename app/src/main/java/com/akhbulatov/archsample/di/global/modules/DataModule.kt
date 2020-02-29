package com.akhbulatov.archsample.di.global.modules

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.data.global.DataManagerImpl
import toothpick.config.Module

class DataModule : Module() {
    init {
        bind(DataManager::class.java).to(DataManagerImpl::class.java).singletonInScope()
    }
}