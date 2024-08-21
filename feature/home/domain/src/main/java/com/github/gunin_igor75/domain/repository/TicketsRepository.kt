package com.github.gunin_igor75.domain.repository

import com.core.common.model.DataResult


interface TicketsRepository<T> {
    suspend fun getTickets(): DataResult<T>
}