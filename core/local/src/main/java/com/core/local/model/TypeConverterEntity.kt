package com.core.local.model

import androidx.room.TypeConverter
import java.util.Date

class TypeConverterEntity {

    @TypeConverter
    fun toDate(dateLong: Long): Date = Date(dateLong)

    @TypeConverter
    fun fromDate(date: Date?) = date?.time
}