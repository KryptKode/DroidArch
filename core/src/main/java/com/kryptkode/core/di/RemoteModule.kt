package com.kryptkode.core.di

import android.content.Context
import androidx.paging.RemoteMediator
import com.kryptkode.commonandroid.assets.AssetsLoader
import com.kryptkode.core.BuildConfig
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.remote.api.ApiFactory
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.mediator.ApiServiceType
import com.kryptkode.core.remote.mediator.UserRemoteMediator
import com.kryptkode.core.remote.mock.MockApiServer
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {

    @Binds
    fun provideRemoteMediator(userRemoteMediator: UserRemoteMediator): RemoteMediator<Int, DbUser>

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi {
            return Moshi.Builder().build()
        }

        @Provides
        fun provideMockServiceApi(moshi: Moshi, mockApiServer: MockApiServer): UsersServiceApi {
            return if (ApiServiceType.useMock) {
                mockApiServer
            } else {
                ApiFactory.makeUsersService(moshi, BuildConfig.DEBUG)
            }
        }

        @Provides
        @Singleton
        fun provideAssetLoader(@ApplicationContext context: Context): AssetsLoader {
            return AssetsLoader(context)
        }
    }
}
