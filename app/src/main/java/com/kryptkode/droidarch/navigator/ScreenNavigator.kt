package com.kryptkode.droidarch.navigator

import android.app.Activity
import androidx.navigation.findNavController
import com.kryptkode.droidarch.R
import com.kryptkode.users.model.User
import com.kryptkode.users.navigator.UsersNavigator
import javax.inject.Inject

class ScreenNavigator @Inject constructor(private val activity: Activity) :
    UsersNavigator {

    private val navController by lazy {
        activity.findNavController(R.id.nav_host)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun toUserDetail(user: User) {
    }
}
