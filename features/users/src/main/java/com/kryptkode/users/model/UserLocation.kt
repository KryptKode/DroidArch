package com.kryptkode.users.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLocation(
    val state: String = "",
    val street: String = "",
    val city: String = "",
    val timezone: String = "",
    val country: String = "",
) : Parcelable
