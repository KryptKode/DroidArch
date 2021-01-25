package com.kryptkode.users.utils

import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.remote.entities.user.Location
import com.kryptkode.testshared.DataFactory.randomString
import com.kryptkode.users.model.UserDetail
import com.kryptkode.users.model.UserLocation
import java.util.Calendar
import java.util.Date

object FakeData {

    const val SAMPLE_DATE = "Mar 12, 1974"
    const val SAMPLE_DATE_TIME = "Mar 12, 1974 21:15 PM"

    val testDate: Date = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1974)
        set(Calendar.MONTH, 2)
        set(Calendar.DAY_OF_MONTH, 12)
        set(Calendar.HOUR_OF_DAY, 21)
        set(Calendar.MINUTE, 15)
        set(Calendar.SECOND, 8)
        set(Calendar.MILLISECOND, 878)
    }.time

    fun makeFakeDbUser(): DbUser {
        return DbUser(
            id = randomString(),
            lastName = randomString(),
            firstName = randomString(),
            email = randomString(),
            title = randomString(),
            picture = randomString(),
            gender = randomString(),
            phone = randomString(),
            location = makeFakeLocation(),
            registerDate = testDate,
            dateOfBirth = testDate,
        )
    }

    fun makeFakeUserDetail(): UserDetail {
        return UserDetail(
            id = randomString(),
            lastName = randomString(),
            firstName = randomString(),
            email = randomString(),
            title = randomString(),
            picture = randomString(),
            gender = randomString(),
            phone = randomString(),
            location = makeFakeUserLocation(),
            registerDate = SAMPLE_DATE_TIME,
            dateOfBirth = SAMPLE_DATE,
        )
    }

    fun makeFakeLocation(): Location {
        return Location(
            state = randomString(),
            street = randomString(),
            city = randomString(),
            timezone = randomString(),
            country = randomString(),
        )
    }

    fun makeFakeUserLocation(): UserLocation {
        return UserLocation(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
        )
    }
}
