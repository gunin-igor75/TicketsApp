package com.core.network

import com.core.network.dto.OffersContainer
import com.core.network.dto.TicketsContainer
import com.core.network.dto.TicketsOffersContainer

interface NetworkSource {

    suspend fun fetchOffers(): OffersContainer
    suspend fun fetchRecommendationTickets(): TicketsOffersContainer
    suspend fun fetchTickets(): TicketsContainer
}