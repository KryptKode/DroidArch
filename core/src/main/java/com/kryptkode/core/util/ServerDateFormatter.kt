package com.kryptkode.core.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import timber.log.Timber

class ServerDateFormatter @Inject constructor() {
    private val serverDateFormatter = SimpleDateFormat(PATTERN, Locale.ENGLISH)

    private val serverDateParseFormatter = SimpleDateFormat(PATTERN, Locale.ENGLISH)

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
            serverDateFormatter.format(date)
        }
    }

    companion object {
        const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
}
