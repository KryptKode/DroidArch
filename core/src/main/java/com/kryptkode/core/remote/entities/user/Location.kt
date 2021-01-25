package com.kryptkode.core.remote.entities.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @field:Json(name = "state") val state: String = "",
    @field:Json(name = "street") val street: String = "",
    @field:Json(name = "city") val city: String = "",
    @field:Json(name = "timezone") val timezone: String = "",
    @field:Json(name = "country") val country: String = "",
)
