package com.github.gunin_igor75.domain.usecase

import com.github.gunin_igor75.domain.repository.FindCountryRepository

class GetSCountryItems(
    private val repository: FindCountryRepository
) {
    operator fun invoke() = repository.getCountryItems()
}