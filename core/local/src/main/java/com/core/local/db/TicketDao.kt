package com.core.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.local.model.TicketDb
import kotlinx.coroutines.flow.Flow


@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticketDb: TicketDb)

    @Query("SELECT * FROM tickets WHERE id =:id LIMIT 1")
    fun getTicket(id: String): Flow<TicketDb>

}