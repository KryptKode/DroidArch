package com.kryptkode.core.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.kryptkode.core.utils.FakeDataFactory.makeFakeUserRemote
import org.junit.Before
import org.junit.Test

class UserRemoteDbMapperTest {
    private lateinit var sut: UserRemoteDbMapper

    @Before
    fun setUp() {
        sut = UserRemoteDbMapper()
    }

    @Test
    fun `map from remote to db entity maps data`() {
        val testRemoteUser = makeFakeUserRemote()

        val result = sut.mapToEntity(testRemoteUser)

        assertThat(result.id).isEqualTo(testRemoteUser.id)
        assertThat(result.email).isEqualTo(testRemoteUser.email)
        assertThat(result.firstName).isEqualTo(testRemoteUser.firstName)
        assertThat(result.lastName).isEqualTo(testRemoteUser.lastName)
        assertThat(result.title).isEqualTo(testRemoteUser.title)
        assertThat(result.picture).isEqualTo(testRemoteUser.picture)
    }
}
