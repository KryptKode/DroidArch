package com.kryptkode.users.ui.list

sealed class UserListViewIntent {
    object LoadInitial : UserListViewIntent()
}
