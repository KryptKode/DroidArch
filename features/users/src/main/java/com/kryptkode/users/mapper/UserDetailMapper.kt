package com.kryptkode.users.mapper

import com.kryptkode.core.cache.mapper.EntityMapper
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.users.model.UserDetail
import com.kryptkode.users.utils.DisplayedDateFormatter
import com.kryptkode.users.utils.DisplayedDateTimeFormatter
import javax.inject.Inject

class UserDetailMapper @Inject constructor(
    private val locationMapper: UserLocationMapper,
    private val dateFormatter: DisplayedDateFormatter,
    private val dateTimeFormatter: DisplayedDateTimeFormatter
) : EntityMapper<DbUser, UserDetail> {

    override fun mapFromEntity(entity: DbUser): UserDetail {
        return UserDetail(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            email = entity.email,
            title = entity.title,
            picture = entity.picture,
            gender = entity.gender,
            phone = entity.phone,
            location = locationMapper.mapFromEntity(entity.location),
            registerDate = dateTimeFormatter.formatToDisplayedDate(entity.registerDate),
            dateOfBirth = dateFormatter.formatToDisplayedDate(entity.dateOfBirth),
        )
    }

    override fun mapToEntity(entity: UserDetail): DbUser {
        return DbUser(
            id = entity.id,
            lastName = entity.lastName,
            firstName = entity.firstName,
            email = entity.email,
            title = entity.title,
            picture = entity.picture,
            gender = entity.gender,
            phone = entity.phone,
            location = locationMapper.mapToEntity(entity.location),
            registerDate = dateTimeFormatter.parseDisplayedDate(entity.registerDate),
            dateOfBirth = dateFormatter.parseDisplayedDate(entity.dateOfBirth),
        )
    }
}
