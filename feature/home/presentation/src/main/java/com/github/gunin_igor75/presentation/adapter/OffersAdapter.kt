package com.github.gunin_igor75.presentation.adapter

import android.content.res.Resources
import com.core.common.R
import com.core.common.model.ItemLoading
import com.core.common.model.ListItem
import com.github.gunin_igor75.presentation.databinding.OffersItemBinding
import com.github.gunin_igor75.presentation.databinding.OffersItemLoadingBinding
import com.github.gunin_igor75.presentation.model.UiOffer
import com.github.gunin_igor75.presentation.utils.OffersDiffUtilItemCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class OffersAdapter: AsyncListDifferDelegationAdapter<ListItem>(OffersDiffUtilItemCallback) {
    init {
        delegatesManager
            .addDelegate(offersAdapterDelegate())
            .addDelegate(offersLoadingAdapterDelegate())
    }

    private fun offersAdapterDelegate() = adapterDelegateViewBinding<UiOffer, ListItem, OffersItemBinding >(
        { layoutInflater, container -> OffersItemBinding.inflate( layoutInflater, container, false)}
    ){
        bind {
            val resource = binding.root.resources
            with(binding){
                imageView.setBackgroundResource(item.imageResId)
                textViewTitle.text = item.title
                textViewContent.text = item.town
                val priceTicket = getStringPriceTicket(resource, item.price)
                textViewPriceTicket.text = priceTicket
            }
        }
    }

    private fun offersLoadingAdapterDelegate() = adapterDelegateViewBinding<ItemLoading, ListItem, OffersItemLoadingBinding>(
        {layoutInflater, container -> OffersItemLoadingBinding.inflate(layoutInflater, container, false)}
    ) {}

    private fun getStringPriceTicket(
        resource: Resources,
        price: String

    ): String{
        val string = resource.getString(R.string.offer_price)
        return String.format(string, price)
    }
}