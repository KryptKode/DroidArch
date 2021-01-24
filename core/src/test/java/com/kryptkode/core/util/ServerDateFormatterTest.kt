package com.kryptkode.core.util

import com.google.common.truth.Truth.assertThat
import com.kryptkode.core.utils.FakeDataFactory
import com.kryptkode.core.utils.FakeDataFactory.SAMPLE_DATE
import org.junit.Before
import org.junit.Test

class ServerDateFormatterTest {

    private lateinit var sut: ServerDateFormatter

    @Before
    fun setUp() {
        sut = ServerDateFormatter()
    }

    @Test
    fun `parseServerDate with valid date parses`() {
        val testDate = SAMPLE_DATE

        val result = sut.parseServerDate(testDate)
        assertThat(result?.time).isEqualTo(132351308878)
    }

    @Test
    fun `parseServerDate with empty date returns null`() {
        val result = sut.parseServerDate("")
        assertThat(result).isNull()
    }

    @Test
    fun `parseServerDate with badly formatted date returns null`() {
        val testDate = "hello"
        val result = sut.parseServerDate(testDate)
        assertThat(result).isNull()
    }

    @Test
    fun `formatToServerDate with valid date returns correctly formatted string`() {
        val testDate = FakeDataFactory.testDate
        val result = sut.formatToServerDate(testDate)

        assertThat(result).isEqualTo("1974-03-12T21:15:08.878Z")
    }

    @Test
    fun `formatToServerDate with null date returns empty string`() {
        val result = sut.formatToServerDate(null)
        assertThat(result).isEmpty()
    }
}
