package com.kryptkode.users.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.cache.user.UserDao
import com.kryptkode.core.dispatchers.AppDispatchers
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.entities.user.UserDetailResponse
import com.kryptkode.core.remote.mapper.UserResponseDbMapper
import com.kryptkode.testshared.DataFactory.randomString
import com.kryptkode.testshared.MainCoroutineRule
import com.kryptkode.testshared.runBlockingTest
import com.kryptkode.users.mapper.UserDetailMapper
import com.kryptkode.users.model.UserDetail
import com.kryptkode.users.utils.FakeData.FAKE_ID
import com.kryptkode.users.utils.FakeData.makeFakeDbUser
import com.kryptkode.users.utils.FakeData.makeFakeDbUserNoDetails
import com.kryptkode.users.utils.FakeData.makeFakeUserDetail
import com.kryptkode.users.utils.FakeData.makeFakeUserDetailResponse
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetUserDetailUseCaseTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var sut: GetUserDetailUseCase

    @MockK
    lateinit var dispatchers: AppDispatchers

    @MockK
    lateinit var serviceApi: UsersServiceApi

    @MockK
    lateinit var userDao: UserDao

    @MockK
    lateinit var responseDbMapper: UserResponseDbMapper

    @MockK
    lateinit var userMapper: UserDetailMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        sut = GetUserDetailUseCase(dispatchers, serviceApi, userDao, responseDbMapper, userMapper)
        stubAppDispatcher()
    }

    @Test
    fun `execute with details returns data`() = coroutineRule.runBlockingTest {
        val testUserId = randomString()
        val dbUser = makeFakeDbUser()
        val userDetail = makeFakeUserDetail()
        stubGetUserDetailsFlow(testUserId, dbUser)
        stubUserMapper(userDetail)

        val result = sut.execute(testUserId)

        result.test {
            assertThat(expectItem()).isEqualTo(userDetail)
            expectComplete()
        }
    }

    @Test
    fun `execute with details maps data`() = coroutineRule.runBlockingTest {
        val testUserId = randomString()
        val dbUser = makeFakeDbUser()
        val userDetail = makeFakeUserDetail()
        stubGetUserDetailsFlow(testUserId, dbUser)
        stubUserMapper(userDetail)

        val result = sut.execute(testUserId)

        result.test {
            expectEvent()
            verify { userMapper.mapFromEntity(dbUser) }
            expectComplete()
        }
    }

    @Test
    fun `execute with no details returns data`() = coroutineRule.runBlockingTest {
        val testUserId = randomString()
        val dbUser = makeFakeDbUserNoDetails()
        val userDetailResponse = makeFakeUserDetailResponse()
        val userDetail = makeFakeUserDetail()
        stubGetUserDetailsFlow(testUserId, dbUser)
        stubUserService(userDetailResponse)
        stubResponseDbMapper(dbUser)
        stubUserMapper(userDetail)
        stubUpdateUser(dbUser)

        val result = sut.execute(testUserId)

        result.test {
            assertThat(expectItem()).isEqualTo(userDetail)
            expectComplete()
        }
    }

    @Test
    fun `execute with no detail calls remote`() = coroutineRule.runBlockingTest {
        val testUserId = randomString()
        val dbUser = makeFakeDbUserNoDetails()
        val userDetailResponse = makeFakeUserDetailResponse()
        val userDetail = makeFakeUserDetail()
        stubGetUserDetailsFlow(testUserId, dbUser)
        stubUserService(userDetailResponse)
        stubResponseDbMapper(dbUser)
        stubUserMapper(userDetail)
        stubUpdateUser(dbUser)

        val result = sut.execute(testUserId)

        result.test {
            expectItem()
            coVerify { serviceApi.getUserDetails(testUserId) }
            expectComplete()
        }
    }

    @Test
    fun `execute with no detail updates user`() = coroutineRule.runBlockingTest {
        val testUserId = randomString()
        val dbUser = makeFakeDbUserNoDetails()
        val userDetailResponse = makeFakeUserDetailResponse()
        val userDetail = makeFakeUserDetail()
        stubGetUserDetailsFlow(testUserId, dbUser)
        stubUserService(userDetailResponse)
        stubResponseDbMapper(dbUser)
        stubUserMapper(userDetail)
        stubUpdateUser(dbUser)

        val result = sut.execute(testUserId)

        result.test {
            expectItem()
            coVerify { userDao.update(dbUser) }
            expectComplete()
        }
    }

    @Test
    fun `execute on io dispatcher`() = coroutineRule.runBlockingTest {
        val testUserId = randomString()
        val dbUser = makeFakeDbUser()
        val userDetail = makeFakeUserDetail()
        stubGetUserDetailsFlow(testUserId, dbUser)
        stubUserMapper(userDetail)

        val result = sut.execute(testUserId)

        result.test {
            expectEvent()
            verify { dispatchers.io }
            verify(inverse = true) { dispatchers.default }
            verify(inverse = true) { dispatchers.main }
            expectComplete()
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun stubResponseDbMapper(dbUser: DbUser) {
        every {
            responseDbMapper.mapToEntity(any())
        } returns dbUser
    }

    private fun stubUserMapper(userDetail: UserDetail) {
        every {
            userMapper.mapFromEntity(any())
        } returns userDetail
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

    private fun stubGetUserDetailsFlow(
        testUserId: String,
        dbUser: DbUser
    ) {
        every {
            userDao.getUserByIdAsFlow(testUserId)
        } returns flowOf(dbUser)
    }

    private fun stubUpdateUser(dbUser: DbUser) {
        coEvery {
            userDao.update(dbUser)
        } returns FAKE_ID
    }

    private fun stubUserService(userDetailResponse: UserDetailResponse) {
        coEvery {
            serviceApi.getUserDetails(any())
        } returns userDetailResponse
    }
}
