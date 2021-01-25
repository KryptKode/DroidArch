package com.kryptkode.users.mapper

import com.kryptkode.core.cache.mapper.EntityMapper
import com.kryptkode.users.model.User
import com.kryptkode.users.model.UserDetail
import javax.inject.Inject

class UserToDetailMapper @Inject constructor() : EntityMapper<User, UserDetail> {

    override fun mapFromEntity(entity: User): UserDetail {
        return UserDetail(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            email = entity.email,
            title = entity.title,
            picture = entity.picture,
        )
    }

    override fun mapToEntity(entity: UserDetail): User {
        return User(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            email = entity.email,
            title = entity.title,
            picture = entity.picture,
        )
    }
}
