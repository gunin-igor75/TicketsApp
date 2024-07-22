package com.github.gunin_igor75.domain.repository

import com.github.gunin_igor75.domain.model.CountryItem

interface FindCountryRepository {
    fun getCountryItems(): List<CountryItem>
}