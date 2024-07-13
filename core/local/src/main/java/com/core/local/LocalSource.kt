package com.core.local

import kotlinx.coroutines.flow.Flow

interface LocalSource {

    suspend fun saveCityState(city: String)

    fun readCityState(): Flow<String>
}