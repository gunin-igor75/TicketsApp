package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.model.TitleTicketModel
import com.github.gunin_igor75.domain.repository.OffersRepository

class SaveTicket(
    private val repository: OffersRepository<List<Offer>>,
) {
    suspend operator fun invoke(titleTicketModel: TitleTicketModel) = repository.saveDetailTicket(titleTicketModel)
}