package com.github.gunin_igor75.data.repository

import com.core.common.base.BaseService
import com.core.common.model.DataResult
import com.core.network.NetworkSource
import com.github.gunin_igor75.data.mappers.toTickets
import com.github.gunin_igor75.domain.model.FlightRoute
import com.github.gunin_igor75.domain.model.Ticket
import com.github.gunin_igor75.domain.repository.TicketsRepository

class TicketsRepositoryImpl(
    private val networkSource: NetworkSource,

) : TicketsRepository<List<Ticket>>, BaseService() {

    override suspend fun getTickets(): DataResult<List<Ticket>> =
        wrapFetchResult {
            val response = networkSource.fetchTickets()
            val result = response.tickets.toTickets()
            DataResult.Success(result)
        }
}