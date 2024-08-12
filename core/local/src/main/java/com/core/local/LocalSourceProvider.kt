package com.core.local

import kotlinx.coroutines.flow.Flow

interface LocalSourceProvider {

    suspend fun saveCityState(city: String)

    fun readCityState(): Flow<String>
}