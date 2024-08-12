package com.github.gunin_igor75.data.repository


import com.core.common.base.BaseService
import com.core.common.model.DataResult
import com.core.local.datasource.TicketLocalDataSource
import com.core.network.NetworkSource
import com.github.gunin_igor75.data.mappers.toOffers
import com.github.gunin_igor75.data.mappers.toTicketDb
import com.github.gunin_igor75.data.mappers.toTicketModel
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.model.TicketModel
import com.github.gunin_igor75.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class OffersRepositoryImpl(
    private val networkSource: NetworkSource,
    private val ticketDataSource: TicketLocalDataSource,
) : OffersRepository<List<Offer>>, BaseService() {


    override suspend fun saveDetailTicket(ticketModel: TicketModel) {
        ticketDataSource.saveTicket(ticketModel.toTicketDb())
    }

    override fun getTicket(id: String): Flow<TicketModel> = ticketDataSource.getTicket(id)
        .map { it.toTicketModel() }

    override suspend fun getOffers(): DataResult<List<Offer>> =
        wrapFetchResult {
            val response = networkSource.fetchOffers()
            val result = response.offers.toOffers()
            DataResult.Success(result)
        }
}