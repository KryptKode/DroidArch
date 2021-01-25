package com.kryptkode.core.cache.converter

import androidx.room.TypeConverter
import com.kryptkode.core.remote.entities.user.Location
import com.squareup.moshi.Moshi

object LocationConverter {
    private val locationAdapter = Moshi.Builder().build().adapter(Location::class.java)

    @TypeConverter
    @JvmStatic
    fun toLocation(text: String): Location {
        return locationAdapter.fromJson(text) ?: Location()
    }

    @TypeConverter
    @JvmStatic
    fun toText(location: Location): String {
        return locationAdapter.toJson(location)
    }
}
