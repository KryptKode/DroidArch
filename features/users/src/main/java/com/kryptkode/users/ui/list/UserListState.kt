package com.kryptkode.users.ui.list

import androidx.paging.PagingData
import com.kryptkode.users.model.User

data class UserListState(
    val items: PagingData<User> = PagingData.empty()
)
