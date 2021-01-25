package com.kryptkode.core.cache.converter

import androidx.room.TypeConverter
import java.util.Calendar
import java.util.Date

object DateConverter {
    private const val DEFAULT_STAMP = 0L

    @TypeConverter
    @JvmStatic
    fun toDate(timeStamp: Long): Date? {
        if (timeStamp == DEFAULT_STAMP) {
            return null
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        return calendar.time
    }

    @TypeConverter
    @JvmStatic
    fun toText(date: Date?): Long = date?.time ?: DEFAULT_STAMP
}
