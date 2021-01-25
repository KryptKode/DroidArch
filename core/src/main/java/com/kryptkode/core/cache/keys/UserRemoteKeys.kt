package com.kryptkode.core.cache.keys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptkode.core.cache.keys.UserRemoteKeys.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserRemoteKeys(
    @PrimaryKey val userId: String,
    val prevKey: Int?,
    val nextKey: Int?
) {
    companion object {
        const val TABLE_NAME = "user_remote_keys"
    }
}
