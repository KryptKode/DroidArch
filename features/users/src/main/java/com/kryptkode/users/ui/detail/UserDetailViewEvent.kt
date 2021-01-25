package com.kryptkode.users.ui.detail

sealed class UserDetailViewEvent {
    object NavigateBack : UserDetailViewEvent()
}
