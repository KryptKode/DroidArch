package com.kryptkode.users.navigator

import com.kryptkode.users.model.User

interface UsersNavigator {
    fun toUserDetail(user: User)
    fun navigateUp()
}
