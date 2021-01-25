package com.kryptkode.users.mapper

import com.kryptkode.core.cache.mapper.EntityMapper
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.users.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() : EntityMapper<DbUser, User> {

    override fun mapFromEntity(entity: DbUser): User {
        return User(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            email = entity.email,
            title = entity.title,
            picture = entity.picture,
        )
    }

    override fun mapToEntity(entity: User): DbUser {
        return DbUser(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            email = entity.email,
            title = entity.title,
            picture = entity.picture,
        )
    }
}
