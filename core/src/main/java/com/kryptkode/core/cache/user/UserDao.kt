package com.kryptkode.core.cache.user

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.kryptkode.core.cache.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<DbUser> {

    @Query("SELECT * FROM users")
    abstract fun getUsers(): PagingSource<Int, DbUser>

    @Query("SELECT * FROM users WHERE id=:id")
    abstract fun getUserByIdAsFlow(id: String): Flow<DbUser>

    @Query("SELECT * FROM users WHERE id=:id")
    abstract fun getUserById(id: String): DbUser

    @Query("SELECT * FROM users")
    abstract fun getUsersAsList(): List<DbUser>

    @Query("DELETE FROM users")
    abstract suspend fun clearUsers()
}
