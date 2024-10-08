package com.github.gunin_igor75.presentation.adapter

import com.core.common.model.ItemLoading
import com.core.common.model.ListItem
import com.github.gunin_igor75.presentation.databinding.CardDirectFlightsIloadingTemBinding
import com.github.gunin_igor75.presentation.databinding.CardDirectFlightsItemBinding
import com.github.gunin_igor75.presentation.databinding.RecommendationTicketsTbuttonItemBinding
import com.github.gunin_igor75.presentation.databinding.RecommendationTicketsTitleItemBinding
import com.github.gunin_igor75.presentation.model.UiButtonItem
import com.github.gunin_igor75.presentation.model.UiTitleItem
import com.github.gunin_igor75.presentation.model.UiTicketOffer
import com.github.gunin_igor75.presentation.utils.TicketOfferDiffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class CountrySelectedAdapter(
    onClickShowAll: () -> Unit,
) :
    AsyncListDifferDelegationAdapter<ListItem>(TicketOfferDiffUtilCallback) {

    init {
        delegatesManager
            .addDelegate(countrySelectedTitleDelegate())
            .addDelegate(ticketOfferDelegate())
            .addDelegate(ticketOfferLoadingDelegate())
            .addDelegate(
                countrySelectedButtonDelegate(
                    onClickShowAll = onClickShowAll
                )
            )
    }

    private fun countrySelectedTitleDelegate() =
        adapterDelegateViewBinding<UiTitleItem, ListItem, RecommendationTicketsTitleItemBinding>(
            { layoutInflater, container ->
                RecommendationTicketsTitleItemBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
        }


    private fun ticketOfferLoadingDelegate() =
        adapterDelegateViewBinding<ItemLoading, ListItem, CardDirectFlightsIloadingTemBinding>(
            { layoutInflater, container ->
                CardDirectFlightsIloadingTemBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
         }

    private fun ticketOfferDelegate() =
        adapterDelegateViewBinding<UiTicketOffer, ListItem, CardDirectFlightsItemBinding>(
            { layoutInflater, container ->
                CardDirectFlightsItemBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                with(binding){
                    imageViewCircle.setImageResource(item.imageResId)
                    textViewAviaCompany.text = item.title
                    textViewPeriodTimeFlight.text = item.timeRange
                    textViewPriceTicket.text = item.price
                }
            }
        }

    private fun countrySelectedButtonDelegate(onClickShowAll: () -> Unit) =
        adapterDelegateViewBinding<UiButtonItem, ListItem, RecommendationTicketsTbuttonItemBinding>(
            { layoutInflater, container ->
                RecommendationTicketsTbuttonItemBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                binding.root.setOnClickListener {
                    onClickShowAll()
                }
            }
        }
}