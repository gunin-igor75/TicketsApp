package com.github.gunin_igor75.domain.repository

import com.core.common.model.DataResult
import kotlinx.coroutines.flow.Flow
interface OffersRepository<T> {

    suspend fun getOffers(): DataResult<T>
    suspend fun saveCityState(city: String)
    fun readCityState(): Flow<String>
}