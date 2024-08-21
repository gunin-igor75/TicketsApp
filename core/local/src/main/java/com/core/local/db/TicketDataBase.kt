package com.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.local.model.TicketDb

@Database(
    entities = [
        TicketDb::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TicketDataBase: RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}