package com.kryptkode.users.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kryptkode.commonandroid.flowbinding.asFlow
import com.kryptkode.users.domain.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class UserListViewModel @ViewModelInject constructor(
    getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val stateFlow = MutableStateFlow(UserListState())
    val viewState = stateFlow.asFlow()

    init {
        getUsersUseCase.getUsers()
            .cachedIn(viewModelScope)
            .scan(UserListState()) { previous, result ->
                previous.copy(items = result)
            }
            .onEach {
                stateFlow.emit(it)
            }.launchIn(viewModelScope)
    }
}
