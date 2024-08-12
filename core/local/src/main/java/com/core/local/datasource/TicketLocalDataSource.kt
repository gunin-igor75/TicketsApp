package com.core.local.datasource

import com.core.local.db.TicketDao
import com.core.local.model.TicketDb
import kotlinx.coroutines.flow.Flow

class TicketLocalDataSource(
    private val ticketApi: TicketDao,
) {

    suspend fun saveTicket(ticketDb: TicketDb) {
        ticketApi.insert(ticketDb)
    }

    fun getTicket(id: String): Flow<TicketDb> {
        return ticketApi.getTicket(id)
    }
}