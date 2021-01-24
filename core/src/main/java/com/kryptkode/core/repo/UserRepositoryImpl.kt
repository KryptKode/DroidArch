package com.kryptkode.core.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.cache.user.UserDao
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.mapper.UserResponseDbMapper
import com.kryptkode.core.remote.mediator.UserRemoteMediator
import com.kryptkode.core.repo.UserRepository.Companion.PAGE_SIZE
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEmpty

class UserRepositoryImpl @Inject constructor(
    @Named(UsersServiceApi.NAME)
    private val usersServiceApi: UsersServiceApi,
    private val remoteMediator: UserRemoteMediator,
    private val remoteDbMapper: UserResponseDbMapper,
    private val dao: UserDao,
) : UserRepository {

    override fun getUserById(id: String): Flow<DbUser> {
        return dao.getUserByIdAsFlow(id).onEmpty {
            val details = usersServiceApi.getUserDetails(id)
            dao.insert(remoteDbMapper.mapToEntity(details))
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<DbUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 40
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = { dao.getUsers() }
        ).flow
    }
}
