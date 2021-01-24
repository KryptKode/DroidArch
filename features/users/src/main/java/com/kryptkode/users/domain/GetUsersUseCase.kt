package com.kryptkode.users.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.kryptkode.core.cache.user.UserDao
import com.kryptkode.core.dispatchers.AppDispatchers
import com.kryptkode.core.remote.mediator.UserRemoteMediator
import com.kryptkode.users.mapper.UserMapper
import com.kryptkode.users.model.User
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetUsersUseCase @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val remoteMediator: UserRemoteMediator,
    private val dao: UserDao,
    private val userMapper: UserMapper
) {

    @ExperimentalPagingApi
    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = INITIAL_PAGE_SIZE
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { dao.getUsers() }
        ).flow.map { pagingData ->
            pagingData.map { user ->
                userMapper.mapFromEntity(user)
            }
        }.flowOn(dispatchers.io)
    }

    companion object {
        private const val PAGE_SIZE = 30
        private const val INITIAL_PAGE_SIZE = 40
    }
}
