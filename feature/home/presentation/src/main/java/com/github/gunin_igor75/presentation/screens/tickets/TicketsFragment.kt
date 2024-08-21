package com.github.gunin_igor75.presentation.screens.tickets

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.adapter.TicketAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentTicketsBinding
import com.github.gunin_igor75.presentation.utils.MarginItemDecorationTickets
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private val binding: FragmentTicketsBinding by viewBinding()

    private val vm: TicketsViewModel by viewModel()

    private val ticketAdapter by lazy {
        TicketAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeTitleTicketViewModel()
        observeTicketsViewModel()
        clickBack()
    }

    private fun setupRecyclerView() {
        val divider =  resources.getDimensionPixelSize(com.core.common.R.dimen.dp_2x)
        val dividerItemDecoration =  MarginItemDecorationTickets(divider)
        with(binding.recyclerView){
            adapter = ticketAdapter
            addItemDecoration(dividerItemDecoration)
        }
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
                        recyclerView.isVisible = false
                        shimmer.isVisible = true
                        shimmer.startShimmer()
                    }
                }
                if (state.data != null) {
                    with(binding){
                        shimmer.stopShimmer()
                        shimmer.isVisible = false
                        ticketAdapter.items = state.data
                        recyclerView.isVisible = true
                    }
                }
            }
        }
    }

    private fun clickBack() {
        binding.inAppBarTickets.imageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}