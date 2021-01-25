package com.kryptkode.core.remote.entities.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @field:Json(name = "data") val data: List<UserRemote>,
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "limit") val limit: Int,
    @field:Json(name = "offset") val offset: Int,
)
