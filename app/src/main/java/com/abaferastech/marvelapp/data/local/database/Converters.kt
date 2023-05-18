package com.abaferastech.marvelapp.data.local.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun convertDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(long: Long): Date {
        return Date(long)
    }
}