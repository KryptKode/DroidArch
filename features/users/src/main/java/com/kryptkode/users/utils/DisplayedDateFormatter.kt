package com.kryptkode.users.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import timber.log.Timber

class DisplayedDateFormatter @Inject constructor() {
    private val displayedDateFormatter = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)

    fun formatToDisplayedDate(date: Date?): String {
        return if (date == null) {
            ""
        } else {
            displayedDateFormatter.format(date)
        }
    }

    fun parseDisplayedDate(date: String): Date? {
        return try {
            displayedDateFormatter.parse(date)
        } catch (ex: ParseException) {
            Timber.w(ex, "Error parsing date")
            return null
        }
    }
}
