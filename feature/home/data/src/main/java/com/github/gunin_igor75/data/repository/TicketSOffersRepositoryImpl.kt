package com.github.gunin_igor75.data.repository

import com.core.common.base.BaseService
import com.core.common.model.DataResult
import com.core.network.NetworkSource
import com.github.gunin_igor75.data.mappers.toTicketsOffers
import com.github.gunin_igor75.domain.model.TicketsOffers
import com.github.gunin_igor75.domain.repository.TicketOffersRepository

class TicketsOffersRepositoryImp(
    private val networkSource: NetworkSource,
) : TicketOffersRepository<List<TicketsOffers>>, BaseService() {

    override suspend fun getRecommendationTickets(): DataResult<List<TicketsOffers>> =
        wrapFetchResult {
            val response = networkSource.fetchRecommendationTickets()
            val result = response.tickets_offers.toTicketsOffers()
            DataResult.Success(result)
        }
}