package com.kryptkode.users.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.cache.user.UserDao
import com.kryptkode.core.dispatchers.AppDispatchers
import com.kryptkode.core.remote.mediator.UserRemoteMediator
import com.kryptkode.testshared.MainCoroutineRule
import com.kryptkode.users.mapper.UserMapper
import com.kryptkode.users.model.User
import com.kryptkode.users.utils.FakeData.makeFakeDbUser
import com.kryptkode.users.utils.asPage
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetUsersUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @MockK
    lateinit var dispatchers: AppDispatchers

    @MockK
    lateinit var remoteMediator: UserRemoteMediator

    @MockK
    lateinit var userDao: UserDao

    @MockK
    lateinit var userMapper: UserMapper

    private lateinit var sut: GetUsersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = GetUsersUseCase(dispatchers, remoteMediator, userDao, userMapper)
        stubAppDispatcher()
    }

    @Test
    fun `getUser maps data`() {
    }

    private fun stubUserMapper(user: User) {
        every {
            userMapper.mapFromEntity(any())
        } returns user
    }

    private fun stubRemoteMediator() {
        coEvery {
            remoteMediator.initialize()
        } returns RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    private fun stubGetUsers() {
        every {
            userDao.getUsers()
        } returns FakePagingSource()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun stubAppDispatcher() {
        every {
            dispatchers.io
        } returns TestCoroutineDispatcher()

        every {
            dispatchers.default
        } returns TestCoroutineDispatcher()

        every {
            dispatchers.main
        } returns TestCoroutineDispatcher()
    }

    internal class FakePagingSource : PagingSource<Int, DbUser>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DbUser> {
            return List(10) { makeFakeDbUser() }.asPage()
        }
    }
}
