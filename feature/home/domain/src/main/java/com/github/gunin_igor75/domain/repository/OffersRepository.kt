package com.github.gunin_igor75.domain.repository

import com.core.common.model.DataResult
import com.github.gunin_igor75.domain.model.TitleTicketModel
import kotlinx.coroutines.flow.Flow

interface OffersRepository<T> {

    suspend fun getOffers(): DataResult<T>
    suspend fun saveDetailTicket(titleTicketModel: TitleTicketModel)
    fun getTicket(id: String): Flow<TitleTicketModel>
}