package com.kryptkode.core.remote.mapper

import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.remote.entities.user.UserRemote

class UserRemoteDbMapper : RemoteMapper<UserRemote, DbUser> {
    override fun mapToEntity(remote: UserRemote): DbUser {
        return DbUser(
            id = remote.id,
            lastName = remote.lastName,
            firstName = remote.firstName,
            email = remote.email,
            title = remote.title,
            picture = remote.picture,
        )
    }
}
