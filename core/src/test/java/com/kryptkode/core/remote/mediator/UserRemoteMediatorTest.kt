package com.kryptkode.core.remote.mediator

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.kryptkode.core.cache.AppDatabase
import com.kryptkode.core.remote.api.UsersServiceApi
import com.kryptkode.core.remote.mapper.UserRemoteDbMapper
import com.kryptkode.core.utils.TestUserServiceApiFactory
import com.kryptkode.testshared.MainCoroutineRule
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UserRemoteMediatorTest {

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var database: AppDatabase
    private lateinit var serviceApi: UsersServiceApi
    private lateinit var mapper: UserRemoteDbMapper
    private lateinit var sut: UserRemoteMediator

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        serviceApi = TestUserServiceApiFactory.makeUserService(mockWebServer)
        mapper = UserRemoteDbMapper()

        sut = UserRemoteMediator(serviceApi, database, mapper)
    }

//    @Test
//    fun `getRemoteKeyForFirstItem with empty db returns null`() = coroutineRule.runBlockingTest {
//
//        val pageState = PagingState(
//            pages = listOf(List(10) { makeFakeDbUser() }).asPages(),
//            anchorPosition = 10,
//            config = PagingConfig(pageSize = 10),
//            leadingPlaceholderCount = 10
//        )
//
//        sut.getRemoteKeyForFirstItem(pageState)
//    }

    @Test
    fun test() {
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
