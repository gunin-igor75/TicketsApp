package com.core.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "tickets")
@TypeConverters(TypeConverterEntity::class)
data class TicketDb(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "city_from")
    val cityFrom: String? = null,
    @ColumnInfo(name = "city_to")
    val cityTo: String? = null,
    val date: Date? = null,
)
