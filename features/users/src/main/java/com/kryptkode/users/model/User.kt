package com.kryptkode.users.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val lastName: String,
    val firstName: String,
    val email: String,
    val title: String,
    val picture: String,
) : Parcelable
