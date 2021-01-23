package com.kryptkode.core.cache.keys

import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.core.cache.base.BaseDao

@Dao
abstract class UserRemoteKeysDao : BaseDao<UserRemoteKeys> {

    @Query("SELECT * FROM user_remote_keys WHERE userId = :userId")
    abstract suspend fun getRemoteKeysById(userId: String): UserRemoteKeys?

    @Query("DELETE FROM user_remote_keys")
    abstract suspend fun clearRemoteKeys()
}
