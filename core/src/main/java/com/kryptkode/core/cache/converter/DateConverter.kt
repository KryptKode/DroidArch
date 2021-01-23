package com.kryptkode.core.cache.converter

import androidx.room.TypeConverter
import java.util.Calendar
import java.util.Date

object DateConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(timeStamp: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        return calendar.time
    }

    @TypeConverter
    @JvmStatic
    fun toText(date: Date): Long = date.time
}
