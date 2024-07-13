package com.github.gunin_igor75.domain.repository
import com.core.common.model.DataResult

interface TicketSOffersRepository<T> {
    suspend fun getRecommendationTickets(): DataResult<T>
}