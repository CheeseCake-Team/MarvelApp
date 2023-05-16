package com.abaferastech.marvelapp.utilities

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class Converters {

    @TypeConverter
    fun convertDateToLong(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(long: Long): Date{
        return Date(long)
    }
}