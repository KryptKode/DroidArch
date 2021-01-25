package com.kryptkode.users.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DisplayedDateTimeFormatterTest {

    private lateinit var sut: DisplayedDateTimeFormatter

    @Before
    fun setUp() {
        sut = DisplayedDateTimeFormatter(FakeData.defaultTimeZone)
    }

    @Test
    fun `parseDisplayedDate with valid date parses`() {
        val testDate = FakeData.SAMPLE_DATE_TIME

        val result = sut.parseDisplayedDate(testDate)
        assertThat(result?.time).isEqualTo(132354900000)
    }

    @Test
    fun `parseDisplayedDate with empty date returns null`() {
        val result = sut.parseDisplayedDate("")
        assertThat(result).isNull()
    }

    @Test
    fun `parseDisplayedDate with badly formatted date returns null`() {
        val testDate = "hello"
        val result = sut.parseDisplayedDate(testDate)
        assertThat(result).isNull()
    }

    @Test
    fun `formatToDisplayedDate with valid date returns correctly formatted string`() {
        val testDate = FakeData.fakeDate
        val result = sut.formatToDisplayedDate(testDate)

        assertThat(result).isEqualTo("Mar 12, 1974 21:15 PM")
    }

    @Test
    fun `formatToDisplayedDate with null date returns empty string`() {
        val result = sut.formatToDisplayedDate(null)
        assertThat(result).isEmpty()
    }
}
