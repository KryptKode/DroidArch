package com.kryptkode.core.utils

import com.kryptkode.core.remote.api.UsersServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TestUserServiceApiFactory {

    fun makeUserService(mockWebServer: MockWebServer): UsersServiceApi {
        return makeUserService(mockWebServer, makeOkHttpClient(makeLoggingInterceptor()))
    }

    private fun makeUserService(
        mockWebServer: MockWebServer,
        okHttpClient: OkHttpClient
    ): UsersServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(UsersServiceApi::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return logging
    }
}
