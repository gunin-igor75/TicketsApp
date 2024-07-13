package com.github.gunin_igor75.data.repository


import com.core.common.base.BaseService
import com.core.common.model.DataResult
import com.core.local.LocalSource
import com.core.network.NetworkSource
import com.github.gunin_igor75.data.mappers.toOffers
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow


class OffersRepositoryImpl(
    private val networkSource: NetworkSource,
    private val localSource:  LocalSource,
) : OffersRepository<List<Offer>>, BaseService() {


    override suspend fun saveCityState(city: String) {
        localSource.saveCityState(city)
    }

    override fun readCityState(): Flow<String> {
        return localSource.readCityState()
    }

    override suspend fun getOffers(): DataResult<List<Offer>> =
        wrapFetchResult {
            val response = networkSource.fetchOffers()
            val result = response.offers.toOffers()
            DataResult.Success(result)
        }
}