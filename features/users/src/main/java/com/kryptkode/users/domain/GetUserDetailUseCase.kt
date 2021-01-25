package com.kryptkode.users.domain

import com.kryptkode.core.cache.user.UserDao
import com.kryptkode.core.dispatchers.AppDispatchers
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.mapper.UserResponseDbMapper
import com.kryptkode.users.mapper.UserDetailMapper
import com.kryptkode.users.model.UserDetail
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetUserDetailUseCase @Inject constructor(
    private val dispatchers: AppDispatchers,
    private val usersServiceApi: UsersServiceApi,
    private val dao: UserDao,
    private val responseDbMapper: UserResponseDbMapper,
    private val userMapper: UserDetailMapper
) {

    fun execute(userId: String): Flow<UserDetail> {
        return dao.getUserByIdAsFlow(userId)
            .flatMapMerge {
                if (it.noDetails) {
                    val remoteUser = usersServiceApi.getUserDetails(userId)
                    dao.update(responseDbMapper.mapToEntity(remoteUser))
                    dao.getUserByIdAsFlow(userId)
                } else {
                    flowOf(it)
                }
            }
            .map { userMapper.mapFromEntity(it) }
            .flowOn(dispatchers.io)
    }
}
