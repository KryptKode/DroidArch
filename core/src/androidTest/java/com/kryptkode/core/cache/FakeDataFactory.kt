package com.kryptkode.core.cache

import com.kryptkode.core.cache.keys.UserRemoteKeys
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.remote.entities.user.Location
import com.kryptkode.core.remote.entities.user.UserDetailResponse
import com.kryptkode.core.remote.entities.user.UserRemote
import com.kryptkode.testshared.DataFactory.randomInt
import com.kryptkode.testshared.DataFactory.randomString
import java.util.Calendar
import java.util.Date

object FakeDataFactory {

    const val SAMPLE_DATE = "1974-03-12T21:15:08.878Z"

    val testDate: Date = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1974)
        set(Calendar.MONTH, 2)
        set(Calendar.DAY_OF_MONTH, 12)
        set(Calendar.HOUR_OF_DAY, 21)
        set(Calendar.MINUTE, 15)
        set(Calendar.SECOND, 8)
        set(Calendar.MILLISECOND, 878)
    }.time

    fun makeFakeUserRemote(): UserRemote {
        return UserRemote(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
        )
    }

    fun makeFakeUserResponse(): UserDetailResponse {
        return UserDetailResponse(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            Location(
                randomString(),
                randomString(),
                randomString(),
                randomString(),
                randomString()
            ),
            SAMPLE_DATE,
            SAMPLE_DATE,
        )
    }

    fun makeFakeDbUser(): DbUser {
        return DbUser(
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            randomString(),
            Location(
                randomString(),
                randomString(),
                randomString(),
                randomString(),
                randomString()
            ),
            testDate,
            testDate,
        )
    }

    fun makeFakeUserRemoteKey(): UserRemoteKeys {
        return UserRemoteKeys(
            randomString(),
            randomInt(),
            randomInt()
        )
    }
}
