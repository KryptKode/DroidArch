package com.kryptkode.users.mapper

import com.google.common.truth.Truth.assertThat
import com.kryptkode.users.utils.DisplayedDateFormatter
import com.kryptkode.users.utils.DisplayedDateTimeFormatter
import com.kryptkode.users.utils.FakeData
import org.junit.Before
import org.junit.Test

class UserDetailMapperTest {

    private lateinit var locationMapper: UserLocationMapper
    private lateinit var dateFormatter: DisplayedDateFormatter
    private lateinit var dateTimeFormatter: DisplayedDateTimeFormatter
    private lateinit var sut: UserDetailMapper

    @Before
    fun setUp() {
        locationMapper = UserLocationMapper()
        dateFormatter = DisplayedDateFormatter()
        dateTimeFormatter = DisplayedDateTimeFormatter()
        sut = UserDetailMapper(locationMapper, dateFormatter, dateTimeFormatter)
    }

    @Test
    fun `map from entity maps data`() {
        val userDetail = FakeData.makeFakeDbUser()

        val result = sut.mapFromEntity(userDetail)

        assertThat(result.id).isEqualTo(userDetail.id)
        assertThat(result.email).isEqualTo(userDetail.email)
        assertThat(result.firstName).isEqualTo(userDetail.firstName)
        assertThat(result.lastName).isEqualTo(userDetail.lastName)
        assertThat(result.title).isEqualTo(userDetail.title)
        assertThat(result.picture).isEqualTo(userDetail.picture)
        assertThat(result.phone).isEqualTo(userDetail.phone)
        assertThat(result.gender).isEqualTo(userDetail.gender)
        assertThat(locationMapper.mapToEntity(result.location)).isEqualTo(userDetail.location)
        assertThat(dateTimeFormatter.parseDisplayedDate(result.registerDate)!!.time)
            .isEqualTo(132354900000)
        assertThat(dateFormatter.parseDisplayedDate(result.dateOfBirth)!!.time)
            .isEqualTo(132274800000)
    }

    @Test
    fun `map to entity maps data`() {
        val userDetail = FakeData.makeFakeUserDetail()
        val result = sut.mapToEntity(userDetail)

        assertThat(result.id).isEqualTo(userDetail.id)
        assertThat(result.email).isEqualTo(userDetail.email)
        assertThat(result.firstName).isEqualTo(userDetail.firstName)
        assertThat(result.lastName).isEqualTo(userDetail.lastName)
        assertThat(result.title).isEqualTo(userDetail.title)
        assertThat(result.picture).isEqualTo(userDetail.picture)
        assertThat(result.phone).isEqualTo(userDetail.phone)
        assertThat(result.gender).isEqualTo(userDetail.gender)
        assertThat(locationMapper.mapFromEntity(result.location)).isEqualTo(userDetail.location)
        assertThat(dateTimeFormatter.formatToDisplayedDate(result.registerDate))
            .isEqualTo(userDetail.registerDate)
        assertThat(dateFormatter.formatToDisplayedDate(result.dateOfBirth))
            .isEqualTo(userDetail.dateOfBirth)
    }
}
