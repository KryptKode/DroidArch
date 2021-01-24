package com.kryptkode.core.di

import android.content.Context
import com.kryptkode.commonandroid.assets.AssetsLoader
import com.kryptkode.core.BuildConfig
import com.kryptkode.core.remote.api.ApiFactory
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.mock.MockApiServer
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Binds
    @Singleton
    @Named(MockApiServer.NAME)
    fun provideMockServiceApi(mockApiServer: MockApiServer): UsersServiceApi

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder().build()
        }

        @Provides
        @Singleton
        @Named(UsersServiceApi.NAME)
        fun provideUserService(moshi: Moshi): UsersServiceApi {
            return ApiFactory.makeUsersService(moshi, BuildConfig.DEBUG)
        }

        @Provides
        @Singleton
        fun provideAssetLoader(@ApplicationContext context: Context): AssetsLoader {
            return AssetsLoader(context)
        }
    }
}
