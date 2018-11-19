package com.akhbulatov.archsample.di.global.modules

import com.akhbulatov.archsample.data.favorites.FavoritesRepositoryImpl
import com.akhbulatov.archsample.data.users.UsersRepositoryImpl
import com.akhbulatov.archsample.domain.repositories.FavoritesRepository
import com.akhbulatov.archsample.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    @Singleton
    fun provideUsersRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    @Singleton
    fun provideFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository
}