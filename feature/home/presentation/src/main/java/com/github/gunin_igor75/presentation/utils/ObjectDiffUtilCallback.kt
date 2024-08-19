package com.github.gunin_igor75.presentation.utils

import com.core.common.base.BaseDiffUtilItemCallback
import com.core.common.model.ListItem
import com.github.gunin_igor75.presentation.model.UiCountryItem
import com.github.gunin_igor75.presentation.model.UiOffer
import com.github.gunin_igor75.presentation.model.UiTicket
import com.github.gunin_igor75.presentation.model.UiTicketOffer

object OffersDiffUtilItemCallback : BaseDiffUtilItemCallback() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        if (oldItem is UiOffer && newItem is UiOffer) {
            return oldItem == newItem
        }
        return super.areContentsTheSame(oldItem, newItem)
    }
}

object CountryItemDiffUtilItemCallback : BaseDiffUtilItemCallback() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        if (oldItem is UiCountryItem && newItem is UiCountryItem) {
            return oldItem == newItem
        }
        return super.areContentsTheSame(oldItem, newItem)
    }
}

object TicketOfferDiffUtilCallback: BaseDiffUtilItemCallback(){
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        if (oldItem is UiTicketOffer && newItem is UiTicketOffer) {
            return oldItem == newItem
        }
        return super.areContentsTheSame(oldItem, newItem)
    }
}

object TicketDiffUtilCallBack: BaseDiffUtilItemCallback(){
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        if (oldItem is UiTicket && newItem is UiTicket) {
            return oldItem == newItem
        }
        return super.areContentsTheSame(oldItem, newItem)
    }
}