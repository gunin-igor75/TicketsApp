package com.github.gunin_igor75.presentation.screens.tickets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentTicketsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private val binding: FragmentTicketsBinding by viewBinding()

    private val vm: TicketsViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTitleTicketViewModel()
        observeTicketsViewModel()
    }

    private fun observeTitleTicketViewModel() {
        lifecycleScope.launch {
            vm.titleTicketState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {
                binding.inAppBarTickets.textViewTitle.text = it.title
                binding.inAppBarTickets.textViewDetails.text = it.description
            }
        }
    }

    private fun observeTicketsViewModel() {
        lifecycleScope.launch {
            vm.ticketsState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest { state ->
                if (state.loading) {
                    with(binding){
                        recyclerView.visibility = View.GONE
                        shimmer.visibility = View.VISIBLE
                        shimmer.startShimmer()
                    }
                } else {
                    with(binding){
                        shimmer.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        shimmer.stopShimmer()
                    }
                }
            }
        }
    }


    private companion object {
        const val TAG = "TicketsFragment"
    }


}