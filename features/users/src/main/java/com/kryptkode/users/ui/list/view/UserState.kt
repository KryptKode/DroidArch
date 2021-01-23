
package com.kryptkode.users.ui.list.view

import androidx.paging.PagingData
import com.kryptkode.basemvi.MviViewState
import com.kryptkode.users.model.User

data class UserState(
    val items: PagingData<User>
) : MviViewState
