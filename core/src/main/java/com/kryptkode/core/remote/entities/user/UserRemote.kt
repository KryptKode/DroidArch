package com.kryptkode.core.remote.entities.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRemote(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "lastName") val lastName: String,
    @field:Json(name = "firstName") val firstName: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "picture") val picture: String,
)
