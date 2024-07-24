package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FindCountryRepository
import kotlinx.coroutines.flow.flow

class GetCountryItems(
    private val repository: FindCountryRepository,
) {
    operator fun invoke() = flow {
        emit(repository.getCountryItems())
    }
}