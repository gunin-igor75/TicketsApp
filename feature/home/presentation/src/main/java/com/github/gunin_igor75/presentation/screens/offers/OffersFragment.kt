package com.github.gunin_igor75.presentation.screens.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.core.common.R
import com.core.common.utils.KeyBoardFilter
import com.github.gunin_igor75.presentation.databinding.FragmentOffersBinding
import com.github.gunin_igor75.presentation.delegates.offersAdapterDelegate
import com.github.gunin_igor75.presentation.delegates.offersLoadingAdapterDelegate
import com.github.gunin_igor75.presentation.utils.MarginItemDecoration
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersFragment : Fragment() {

    private var _binding: FragmentOffersBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentOffersBinding is null")

    private val vm: OffersViewModel by viewModel()

    private val filter = KeyBoardFilter()

    private val adapterOffers by lazy {
        ListDelegationAdapter(
            offersAdapterDelegate(),
            offersLoadingAdapterDelegate()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOffersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditTextCityFrom()
        setupRecyclerView()
        observeOffers()
    }

    private fun setupRecyclerView() {
        with(binding) {
            val divider = resources.getDimensionPixelSize(R.dimen.dp_8x)
            val dividerItemDecoration = MarginItemDecoration(divider)
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.adapter = adapterOffers
        }
    }

    private fun observeOffers() {
        lifecycleScope.launch {
            vm.offersState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                if (it.data != null) {
                    adapterOffers.items = it.data
                    adapterOffers.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setupEditTextCityFrom() {
        lifecycleScope.launch {
            vm.cityStorage.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                binding.inInputCity.textInputEditTextCityFrom.filters = arrayOf(filter)
                binding.inInputCity.textInputEditTextCityFrom.setText(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}