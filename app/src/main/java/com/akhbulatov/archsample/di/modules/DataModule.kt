package com.akhbulatov.archsample.di.modules

import com.akhbulatov.archsample.data.favorites.FavoritesRepositoryImpl
import com.akhbulatov.archsample.data.users.UsersRepositoryImpl
import com.akhbulatov.archsample.domain.repositories.FavoritesRepository
import com.akhbulatov.archsample.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindUsersRepository(repository: UsersRepositoryImpl): UsersRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository
}