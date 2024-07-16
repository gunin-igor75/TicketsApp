package com.github.gunin_igor75.presentation.screens.offers

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.common.R
import com.github.gunin_igor75.presentation.databinding.OffersItemBinding
import com.github.gunin_igor75.presentation.model.UiOffer


class OffersAdapter :
    ListAdapter<UiOffer, OffersAdapter.OffersItemViewHolder>(DaysItemDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersItemViewHolder {
        val view = OffersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OffersItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: OffersItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object DaysItemDiffCallback : DiffUtil.ItemCallback<UiOffer>() {
        override fun areItemsTheSame(oldItem: UiOffer, newItem: UiOffer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiOffer, newItem: UiOffer): Boolean {
            return oldItem == newItem
        }

    }

    inner class OffersItemViewHolder(private val binding: OffersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiOffer) {
            val resource = binding.root.resources
            binding.imageView.setBackgroundResource(item.imageResId)
            binding.textViewTitle.text = item.title
            binding.textViewContent.text = item.town
            val priceTicket = getStringPriceTicket(resource, item.price)
            binding.textViewPriceTicket.text = priceTicket
        }

        private fun getStringPriceTicket(
            resource: Resources,
            price: String

        ): String{
            val string = resource.getString(R.string.offer_price)
            return String.format(string, price)
        }
    }
}