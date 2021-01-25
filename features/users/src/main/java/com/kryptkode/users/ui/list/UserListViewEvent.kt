package com.kryptkode.users.ui.list

import com.kryptkode.users.model.User

sealed class UserListViewEvent {
    data class ClickUser(val user: User) : UserListViewEvent()
    data class ShowError(val error: Throwable) : UserListViewEvent()
}
