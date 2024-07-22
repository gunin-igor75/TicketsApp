package com.github.gunin_igor75.presentation.adapter

import com.core.common.model.ListItem
import com.github.gunin_igor75.presentation.databinding.FindRecommendationItemBinding
import com.github.gunin_igor75.presentation.model.UiCountryItem
import com.github.gunin_igor75.presentation.utils.CountryItemDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class CountryItemAdapter(
    onClickCity: (Int) -> Unit,
) :
    AsyncListDifferDelegationAdapter<ListItem>(CountryItemDiffUtilItemCallback) {

    init {
        delegatesManager.addDelegate(
            countryItemDelegate(
                onClickCity = onClickCity
            )
        )
    }

    private fun countryItemDelegate(
        onClickCity: (Int) -> Unit,
    ) =
        adapterDelegateViewBinding<UiCountryItem, ListItem, FindRecommendationItemBinding>(
            { layoutInflater, container ->
                FindRecommendationItemBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                val resources = binding.root.resources
                with(binding) {
                    imageView.setImageResource(item.imageResId)
                    textViewCity.text = resources.getText(item.cityResId)
                }
                binding.root.setOnClickListener {
                    onClickCity(item.cityResId)
                }
            }
        }
}