package com.kryptkode.users.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.kryptkode.users.domain.GetUserDetailUseCase
import timber.log.Timber

class UserDetailViewModel @ViewModelInject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    fun getUserDetails(userId: String) {
        Timber.d("User id: $userId")
    }
}
