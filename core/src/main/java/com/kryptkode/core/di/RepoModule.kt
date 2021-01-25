package com.kryptkode.core.di

import com.kryptkode.core.repo.UserRepository
import com.kryptkode.core.repo.UserRepositoryImpl
import com.kryptkode.core.util.ServerDateFormatter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    @Singleton
    fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        @Singleton
        fun provideServerDateFormatter(): ServerDateFormatter {
            return ServerDateFormatter()
        }
    }
}
