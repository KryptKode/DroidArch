package com.kryptkode.users.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetail(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val title: String,
    val picture: String,
    val phone: String,
    val gender: String,
    val location: UserLocation,
    val registerDate: String,
    val dateOfBirth: String,
) : Parcelable
