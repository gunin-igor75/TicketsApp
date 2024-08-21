package com.github.gunin_igor75.data.repository

import com.core.common.R
import com.github.gunin_igor75.domain.model.CountryItem
import com.github.gunin_igor75.domain.repository.FindCountryRepository

class FindCountryRepositoryImpl : FindCountryRepository {

    private val counties = listOf(
        CountryItem(
            imageResId = R.drawable.im_istanbul,
            cityResId = R.string.istanbul
        ),
        CountryItem(
            imageResId = R.drawable.im_sochi,
            cityResId = R.string.sochi
        ),
        CountryItem(
            imageResId = R.drawable.im_phuket,
            cityResId = R.string.phuket
        )
    )


    override fun getCountryItems(): List<CountryItem> {
        return counties
    }
}