package com.github.gunin_igor75.domain.repository

import com.core.common.model.DataResult
import com.github.gunin_igor75.domain.model.TicketModel
import kotlinx.coroutines.flow.Flow

interface OffersRepository<T> {

    suspend fun getOffers(): DataResult<T>
    suspend fun saveDetailTicket(ticketModel: TicketModel)
    fun getTicket(id: String): Flow<TicketModel>
}