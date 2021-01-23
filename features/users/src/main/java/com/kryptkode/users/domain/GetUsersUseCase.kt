package com.kryptkode.users.domain

import androidx.paging.PagingData
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.dispatchers.AppDispatchers
import com.kryptkode.core.repo.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatchers: AppDispatchers
) {

    fun getUsers(): Flow<PagingData<DbUser>> {
        return userRepository.getUsers().flowOn(dispatchers.io)
    }
}
