package com.kryptkode.users.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.kryptkode.testshared.MainCoroutineRule
import com.kryptkode.users.domain.GetUsersUseCase
import com.kryptkode.users.utils.FakeData.makeUser
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private var listOfUsers = listOf(makeUser(), makeUser())

    @MockK
    lateinit var getUsersUseCase: GetUsersUseCase

    private lateinit var sut: UserListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        stubGetUserUseCase()
        sut = UserListViewModel(getUsersUseCase)
    }

    @Test
    fun `emits state`() {
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    private fun stubGetUserUseCase() {
        every {
            getUsersUseCase.getUsers()
        } returns flowOf(PagingData.from(listOfUsers))
    }
}
