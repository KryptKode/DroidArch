package com.kryptkode.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import timber.log.Timber

class ServerDateFormatter constructor(
    private val parserTimeZone: TimeZone = TimeZone.getTimeZone("GMT")
) {
    private val serverDateParseFormatter = SimpleDateFormat(PATTERN, Locale.ENGLISH).apply {
        timeZone = parserTimeZone
    }

    fun parseServerDate(date: String): Date? {
        return try {
            serverDateParseFormatter.parse(date)
        } catch (ex: ParseException) {
            Timber.w(ex, "Error parsing date")
            return null
        }
    }

    fun formatToServerDate(date: Date?): String {
        return if (date == null) {
            ""
        } else {
            serverDateParseFormatter.format(date)
        }
    }

    companion object {
        const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
}
