package com.kryptkode.users.ui.list.view

import com.kryptkode.basemvi.MviIntent
import com.kryptkode.users.model.User

sealed class UserListViewIntent : MviIntent {
    data class ClickUser(val user: User) : UserListViewIntent()
}
