package com.kryptkode.core.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.kryptkode.core.util.ServerDateFormatter
import com.kryptkode.core.utils.FakeDataFactory
import org.junit.Before
import org.junit.Test

class UserResponseDbMapperTest {

    private lateinit var sut: UserResponseDbMapper
    private lateinit var serverDateFormatter: ServerDateFormatter

    @Before
    fun setUp() {
        serverDateFormatter = ServerDateFormatter()
        sut = UserResponseDbMapper(serverDateFormatter)
    }

    @Test
    fun `map from remote to db entity maps data`() {
        val testRemoteUser = FakeDataFactory.makeFakeUserResponse()

        val result = sut.mapToEntity(testRemoteUser)

        assertThat(result.id).isEqualTo(testRemoteUser.id)
        assertThat(result.email).isEqualTo(testRemoteUser.email)
        assertThat(result.firstName).isEqualTo(testRemoteUser.firstName)
        assertThat(result.lastName).isEqualTo(testRemoteUser.lastName)
        assertThat(result.title).isEqualTo(testRemoteUser.title)
        assertThat(result.picture).isEqualTo(testRemoteUser.picture)
        assertThat(result.phone).isEqualTo(testRemoteUser.phone)
        assertThat(result.gender).isEqualTo(testRemoteUser.gender)
        assertThat(result.location).isEqualTo(testRemoteUser.location)
        assertThat(serverDateFormatter.formatToServerDate(result.registerDate))
            .isEqualTo(testRemoteUser.registerDate)
        assertThat(serverDateFormatter.formatToServerDate(result.dateOfBirth))
            .isEqualTo(testRemoteUser.dateOfBirth)
    }
}
