package com.kryptkode.core.remote.mapper

import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.remote.entities.user.UserDetailResponse
import com.kryptkode.core.util.ServerDateFormatter
import javax.inject.Inject

class UserResponseDbMapper @Inject constructor(
    private val serverDateFormatter: ServerDateFormatter
) : RemoteMapper<UserDetailResponse, DbUser> {
    override fun mapToEntity(remote: UserDetailResponse): DbUser {
        return DbUser(
            id = remote.id,
            lastName = remote.lastName,
            firstName = remote.firstName,
            email = remote.email,
            title = remote.title,
            picture = remote.picture,
            gender = remote.gender,
            phone = remote.phone,
            location = remote.location,
            registerDate = serverDateFormatter.parseServerDate(remote.registerDate),
            dateOfBirth = serverDateFormatter.parseServerDate(remote.dateOfBirth),
        )
    }
}
