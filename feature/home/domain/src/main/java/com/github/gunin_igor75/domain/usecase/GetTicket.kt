package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.model.TicketModel
import com.github.gunin_igor75.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow

class GetTicket(
    private val repository: OffersRepository<List<Offer>>,
) {
    operator fun invoke(id: String): Flow<TicketModel> = repository.getTicket(id)
}