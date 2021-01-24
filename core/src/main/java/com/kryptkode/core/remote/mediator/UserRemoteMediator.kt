package com.kryptkode.core.remote.mediator

import androidx.annotation.VisibleForTesting
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kryptkode.core.cache.AppDatabase
import com.kryptkode.core.cache.keys.UserRemoteKeys
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.mapper.UserRemoteDbMapper
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject
import retrofit2.HttpException

private const val STARTING_PAGE_INDEX = 0

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val service: UsersServiceApi,
    private val database: AppDatabase,
    private val mapper: UserRemoteDbMapper,
) : RemoteMediator<Int, DbUser>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DbUser>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                // The LoadType is PREPEND so some data was loaded before,
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    // so we should have been able to get remote keys
                    // If the remoteKeys are null, then we're an invalid state and we have a bug
                    ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
                // If the previous key is null, then we can't request more data
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                getRemoteKeyForLastItem(state)?.nextKey
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
            }
        }

        try {

            val apiResponse = service.getUsers(page, state.config.pageSize)
            val data = apiResponse.data
            val endOfPaginationReached = data.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userRemoteKeysDao().clearRemoteKeys()
                    database.userDao().clearUsers()
                }

                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                database.userRemoteKeysDao().insert(
                    data.map {
                        UserRemoteKeys(userId = it.id, prevKey = prevKey, nextKey = nextKey)
                    }
                )

                database.userDao().insert(
                    data.map {
                        mapper.mapToEntity(it)
                    }
                )
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    @VisibleForTesting
    suspend fun getRemoteKeyForLastItem(state: PagingState<Int, DbUser>): UserRemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { farmer ->
                // Get the remote keys of the last item retrieved
                database.userRemoteKeysDao().getRemoteKeysById(farmer.id)
            }
    }

    @VisibleForTesting
    suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, DbUser>): UserRemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { farmer ->
                // Get the remote keys of the first items retrieved
                database.userRemoteKeysDao().getRemoteKeysById(farmer.id)
            }
    }

    @VisibleForTesting
    suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, DbUser>
    ): UserRemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.userRemoteKeysDao().getRemoteKeysById(id)
            }
        }
    }
}
