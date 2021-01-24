package com.kryptkode.core.remote.api

import com.kryptkode.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AppIdHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
            .addHeader(APP_ID, BuildConfig.DUMMY_APP_ID)
        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val APP_ID = "app-id"
    }
}
