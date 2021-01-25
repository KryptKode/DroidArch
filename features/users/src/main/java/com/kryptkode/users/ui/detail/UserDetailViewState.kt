package com.kryptkode.users.ui.detail

import com.kryptkode.users.model.UserDetail

data class UserDetailViewState(
    val userDetail: UserDetail = UserDetail(),
    val loading: Boolean = true,
    val error: Boolean = false,
    val errorMessage: String = ""
)
