package com.kryptkode.users.mapper

import com.kryptkode.core.cache.mapper.EntityMapper
import com.kryptkode.core.remote.entities.user.Location
import com.kryptkode.users.model.UserLocation
import javax.inject.Inject

class UserLocationMapper @Inject constructor() : EntityMapper<Location, UserLocation> {

    override fun mapFromEntity(entity: Location): UserLocation {
        return UserLocation(
            state = entity.state,
            street = entity.street,
            city = entity.city,
            timezone = entity.timezone,
            country = entity.country,
        )
    }

    override fun mapToEntity(entity: UserLocation): Location {
        return Location(
            state = entity.state,
            street = entity.street,
            city = entity.city,
            timezone = entity.timezone,
            country = entity.country,
        )
    }
}
