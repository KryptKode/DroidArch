package com.kryptkode.core.cache.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.kryptkode.core.cache.AppDatabase
import com.kryptkode.core.cache.FakeDataFactory.makeFakeDbUser
import com.kryptkode.testshared.DataFactory.randomString
import com.kryptkode.testshared.MainCoroutineRule
import com.kryptkode.testshared.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var database: AppDatabase
    private lateinit var sut: UserDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        sut = database.userDao()
    }

    @Test
    fun insert_oneUser_addedToDb() = coroutineRule.runBlockingTest {

        val user = makeFakeDbUser()
        sut.insert(user)

        val result = sut.getUsersAsList()
        assertThat(result[0]).isEqualTo(user)
    }

    @Test
    fun insert_listOfUsers_addedToDb() = coroutineRule.runBlockingTest {
        val users = listOf(
            makeFakeDbUser(),
            makeFakeDbUser(),
            makeFakeDbUser()
        )
        sut.insert(users)

        val result = sut.getUsersAsList()
        assertThat(result.toTypedArray() contentEquals users.toTypedArray()).isTrue()
    }

    @Test
    fun update_oneUser_returnsUpdatedData() = coroutineRule.runBlockingTest {
        val user = makeFakeDbUser()
        sut.insert(user)

        val updatedFirstName = randomString()
        val updatedUser = user.copy(firstName = updatedFirstName)

        sut.update(updatedUser)

        val result = sut.getUserById(user.id)

        assertThat(result).isEqualTo(updatedUser)
    }

    @Test
    fun update_listOfUsers_returnsUpdatedData() = coroutineRule.runBlockingTest {
        val users = listOf(
            makeFakeDbUser(),
            makeFakeDbUser(),
            makeFakeDbUser()
        )
        sut.insert(users)

        val updatedLastName = randomString()

        val updatedUsers = users.take(2).map { it.copy(lastName = updatedLastName) }
        sut.update(updatedUsers)

        updatedUsers.onEach { user ->
            val result = sut.getUserById(user.id)
            assertThat(result.lastName).isEqualTo(updatedLastName)
        }
    }

    @Test
    fun getUsers_ReturnsData() = coroutineRule.runBlockingTest {
        val users = listOf(
            makeFakeDbUser(),
            makeFakeDbUser(),
            makeFakeDbUser()
        )
        sut.insert(users)

        val result = sut.getUsersAsList()
        assertThat(result.toTypedArray() contentEquals users.toTypedArray()).isTrue()
    }

    @Test
    fun clearUsers_deletesAllUsers() = coroutineRule.runBlockingTest {
        val users = listOf(
            makeFakeDbUser(),
            makeFakeDbUser(),
            makeFakeDbUser()
        )
        sut.insert(users)

        sut.clearUsers()

        val result = sut.getUsersAsList()
        assertThat(result).isEmpty()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
