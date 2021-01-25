package com.kryptkode.users.mapper

import com.google.common.truth.Truth.assertThat
import com.kryptkode.users.utils.FakeData
import org.junit.Before
import org.junit.Test

class UserLocationMapperTest {

    private lateinit var sut: UserLocationMapper

    @Before
    fun setUp() {
        sut = UserLocationMapper()
    }

    @Test
    fun `map from entity maps data`() {
        val location = FakeData.makeFakeLocation()

        val result = sut.mapFromEntity(location)

        assertThat(result.state).isEqualTo(location.state)
        assertThat(result.city).isEqualTo(location.city)
        assertThat(result.country).isEqualTo(location.country)
        assertThat(result.street).isEqualTo(location.street)
        assertThat(result.timezone).isEqualTo(location.timezone)
    }

    @Test
    fun `map to entity maps data`() {
        val location = FakeData.makeFakeUserLocation()

        val result = sut.mapToEntity(location)

        assertThat(result.state).isEqualTo(location.state)
        assertThat(result.city).isEqualTo(location.city)
        assertThat(result.country).isEqualTo(location.country)
        assertThat(result.street).isEqualTo(location.street)
        assertThat(result.timezone).isEqualTo(location.timezone)
    }
}
