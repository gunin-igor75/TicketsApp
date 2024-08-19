package com.github.gunin_igor75.presentation.adapter

import android.view.View
import androidx.core.view.isVisible
import com.core.common.model.ListItem
import com.github.gunin_igor75.presentation.databinding.CardTicketsItemBinding
import com.github.gunin_igor75.presentation.model.UiTicket
import com.github.gunin_igor75.presentation.utils.TicketDiffUtilCallBack
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class TicketAdapter : AsyncListDifferDelegationAdapter<ListItem>(TicketDiffUtilCallBack) {

    init {
        delegatesManager.addDelegate(
            ticketItemDelegate()
        )
    }

    private fun ticketItemDelegate() =
        adapterDelegateViewBinding<UiTicket, ListItem, CardTicketsItemBinding>(
            { layoutInflater, container ->
                CardTicketsItemBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                with(binding) {
                    textViewBeginFlight.text = item.timeDeparture
                    textViewEndFlight.text = item.timeArrival
                    textViewPriceTicket.text = item.price
                    llNoTransfer.isVisible = item.transfer
                    textViewTotalFlight.text = item.timeFlight
                    textViewAirDeparture.text = item.airportDeparture
                    textViewAirArrival.text = item.airportArrival
                    if (item.badge != null) {
                        frameLayoutBadge.visibility = View.VISIBLE
                        binding.textViewBadge.text = item.badge
                    } else {
                        frameLayoutBadge.visibility = View.GONE
                    }
                }
            }
        }
}