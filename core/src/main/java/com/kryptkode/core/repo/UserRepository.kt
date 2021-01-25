package com.kryptkode.core.repo

import androidx.paging.PagingData
import com.kryptkode.core.cache.user.DbUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<DbUser>>
    fun getUserById(id: String): Flow<DbUser>

    companion object {
        const val PAGE_SIZE = 30
    }
}
