package com.kryptkode.users.utils

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class DisplayedDateFormatterTest {

    private lateinit var sut: DisplayedDateFormatter

    @Before
    fun setUp() {
        sut = DisplayedDateFormatter(FakeData.defaultTimeZone)
    }

    @Test
    fun `parseDisplayedDate with valid date parses`() {
        val testDate = FakeData.SAMPLE_DATE

        val result = sut.parseDisplayedDate(testDate)
        Truth.assertThat(result?.time).isEqualTo(132278400000)
    }

    @Test
    fun `parseDisplayedDate with empty date returns null`() {
        val result = sut.parseDisplayedDate("")
        Truth.assertThat(result).isNull()
    }

    @Test
    fun `parseDisplayedDate with badly formatted date returns null`() {
        val testDate = "hello"
        val result = sut.parseDisplayedDate(testDate)
        Truth.assertThat(result).isNull()
    }

    @Test
    fun `formatToDisplayedDate with valid date returns correctly formatted string`() {
        val testDate = FakeData.fakeDate
        val result = sut.formatToDisplayedDate(testDate)

        Truth.assertThat(result).isEqualTo("Mar 12, 1974")
    }

    @Test
    fun `formatToDisplayedDate with null date returns empty string`() {
        val result = sut.formatToDisplayedDate(null)
        Truth.assertThat(result).isEmpty()
    }
}
