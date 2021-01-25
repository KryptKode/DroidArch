package com.kryptkode.core.remote.mock

import com.kryptkode.commonandroid.assets.AssetsLoader
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.entities.user.UserDetailResponse
import com.kryptkode.core.remote.entities.user.UserResponse
import com.squareup.moshi.Moshi
import javax.inject.Inject
import kotlinx.coroutines.delay

class MockApiServer @Inject constructor(
    private val moshi: Moshi,
    private val assetsLoader: AssetsLoader
) : UsersServiceApi {

    override suspend fun getUserDetails(userId: String): UserDetailResponse {
        delay(DELAY_MILLIS)
        return moshi.adapter(UserDetailResponse::class.java)
            .fromJson(
                assetsLoader.getAssetDataAsString(
                    USER_DETAIL_RESPONSE
                )!!
            )!!
    }

    override suspend fun getUsers(page: Int, limit: Int): UserResponse {
        delay(DELAY_MILLIS)
        return moshi.adapter(UserResponse::class.java)
            .fromJson(
                assetsLoader.getAssetDataAsString(
                    USER_LIST_RESPONSE
                )!!
            )!!
    }

    companion object {
        const val NAME = "mock"
        const val DELAY_MILLIS = 2000L
        const val USER_LIST_RESPONSE = "user_list_success.json"
        const val USER_DETAIL_RESPONSE = "user_details_success.json"
    }
}
