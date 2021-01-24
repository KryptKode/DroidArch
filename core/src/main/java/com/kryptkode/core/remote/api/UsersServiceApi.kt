package com.kryptkode.core.remote.api

import com.kryptkode.core.remote.entities.user.UserDetailResponse
import com.kryptkode.core.remote.entities.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersServiceApi {

    @GET("user")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): UserResponse

    @GET("user/{userId}")
    suspend fun getUserDetails(@Path("userId") userId: String): UserDetailResponse
}
