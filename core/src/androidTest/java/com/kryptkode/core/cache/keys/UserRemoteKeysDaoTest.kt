package com.kryptkode.core.cache.keys

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.kryptkode.core.cache.AppDatabase
import com.kryptkode.core.cache.FakeDataFactory.makeFakeUserRemoteKey
import com.kryptkode.testshared.DataFactory.randomInt
import com.kryptkode.testshared.MainCoroutineRule
import com.kryptkode.testshared.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRemoteKeysDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var database: AppDatabase
    private lateinit var sut: UserRemoteKeysDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        sut = database.userRemoteKeysDao()
    }

    @Test
    fun insert_oneRemoteKey_addedToDb() = coroutineRule.runBlockingTest {

        val remoteKey = makeFakeUserRemoteKey()
        sut.insert(remoteKey)

        val result = sut.getRemoteKeysAsList()
        assertThat(result[0]).isEqualTo(remoteKey)
    }

    @Test
    fun insert_listOfRemoteKeys_addedToDb() = coroutineRule.runBlockingTest {
        val remoteKeys = listOf(
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey()
        )
        sut.insert(remoteKeys)

        val result = sut.getRemoteKeysAsList()
        assertThat(result.toTypedArray() contentEquals remoteKeys.toTypedArray()).isTrue()
    }

    @Test
    fun update_oneRemoteKey_returnsUpdatedData() = coroutineRule.runBlockingTest {
        val remoteKey = makeFakeUserRemoteKey()
        sut.insert(remoteKey)

        val updatedNextKey = randomInt()
        val updatedRemoteKey = remoteKey.copy(nextKey = updatedNextKey)

        sut.update(updatedRemoteKey)

        val result = sut.getRemoteKeysById(remoteKey.userId)

        assertThat(result).isEqualTo(updatedRemoteKey)
    }

    @Test
    fun update_listOfRemoteKeys_returnsUpdatedData() = coroutineRule.runBlockingTest {
        val remoteKeys = listOf(
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey()
        )
        sut.insert(remoteKeys)

        val previousKey = randomInt()

        val updatedRemoteKeys = remoteKeys.take(2).map { it.copy(prevKey = previousKey) }
        sut.update(updatedRemoteKeys)

        updatedRemoteKeys.onEach { remoteKey ->
            val result = sut.getRemoteKeysById(remoteKey.userId)
            assertThat(result!!.prevKey).isEqualTo(previousKey)
        }
    }

    @Test
    fun getRemoteKeys_ReturnsData() = coroutineRule.runBlockingTest {
        val remoteKeys = listOf(
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey()
        )
        sut.insert(remoteKeys)

        val result = sut.getRemoteKeysAsList()
        assertThat(result.toTypedArray() contentEquals remoteKeys.toTypedArray()).isTrue()
    }

    @Test
    fun clearRemoteKeys_deletesAllRemoteKeys() = coroutineRule.runBlockingTest {
        val remoteKeys = listOf(
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey(),
            makeFakeUserRemoteKey()
        )
        sut.insert(remoteKeys)

        sut.clearRemoteKeys()

        val result = sut.getRemoteKeysAsList()
        assertThat(result).isEmpty()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
