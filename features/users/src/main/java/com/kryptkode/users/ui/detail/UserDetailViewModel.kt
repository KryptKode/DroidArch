package com.kryptkode.users.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kryptkode.commonandroid.flowbinding.asFlow
import com.kryptkode.users.domain.GetUserDetailUseCase
import com.kryptkode.users.mapper.UserToDetailMapper
import com.kryptkode.users.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class UserDetailViewModel @ViewModelInject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val userToDetailMapper: UserToDetailMapper
) : ViewModel() {

    private val stateFlow = MutableStateFlow(UserDetailViewState())
    val viewState = stateFlow.asFlow()

    fun getUserDetails(user: User) {
        getUserDetailUseCase.execute(user.id)
            .scan(
                UserDetailViewState(
                    userToDetailMapper
                        .mapFromEntity(user)
                )
            ) { previous, result ->
                previous.copy(userDetail = result)
            }
            .onEach {
                stateFlow.emit(it)
            }.launchIn(viewModelScope)
    }
}
