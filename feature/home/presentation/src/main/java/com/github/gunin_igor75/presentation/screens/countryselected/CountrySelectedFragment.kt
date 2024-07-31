package com.github.gunin_igor75.presentation.screens.countryselected

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.core.common.R
import com.github.gunin_igor75.presentation.adapter.CountrySelectedAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentCountrySelectedBinding
import com.github.gunin_igor75.presentation.utils.MarginItemDecorationRecommendationTickets
import com.github.gunin_igor75.presentation.utils.getDateToday
import com.github.gunin_igor75.presentation.utils.getWeek
import com.github.gunin_igor75.presentation.utils.markWord
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class CountrySelectedFragment : Fragment() {

    private var _binding: FragmentCountrySelectedBinding? = null
    private val binding: FragmentCountrySelectedBinding
        get() = _binding ?: throw IllegalStateException("FragmentCountrySelectedBinding is null")

    private val args by navArgs<CountrySelectedFragmentArgs>()

    private val vm: CountrySelectedViewModel by viewModel()

    private val adapter by lazy {
        CountrySelectedAdapter{
            launchScreenTickets(
                cityFrom = args.cityFrom,
                cityTo = args.cityTo
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCountrySelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleCityFromTo()
        setupRecyclerView()
        observeViewModel()
        observeError()
        setupDateSending(getDateToday())
    }

    private fun setupTitleCityFromTo() {
        binding.textInputEditTextCityFrom.setText(args.cityFrom)
        binding.textInputEditTextCityTo.setText(args.cityTo)
    }

    private fun setupRecyclerView() {
        val divider = resources.getDimensionPixelSize(R.dimen.dp_2x)
        val dividerItemDecoration = MarginItemDecorationRecommendationTickets(divider)
        binding.inTickets.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.inTickets.recyclerView.adapter = adapter

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            vm.ticketsOffers.collect {
                Log.d("CountrySelectedFragment", it.data.toString())
                adapter.items = it.data
            }
        }
    }

    private fun launchScreenTickets(cityFrom: String, cityTo: String) {
        findNavController().navigate(CountrySelectedFragmentDirections.actionCountrySelectedFragmentToTicketsFragment(
            cityFrom = cityFrom,
            cityTo = cityTo
        ))
    }

    private fun observeError() {
        lifecycleScope.launch {
            vm.error.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { isError ->
                if (isError) {
                    val snackBar =
                        Snackbar.make(binding.root, R.string.error, Snackbar.LENGTH_SHORT)
                    snackBar.setBackgroundTint(
                        ContextCompat.getColor(requireContext(), R.color.grey)
                    )
                    snackBar.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_bg))
                    snackBar.show()
                }
            }
        }
    }

    private fun setupDateSending(date: String) {
        val phrase = getWeek(date)
        val spannableString = markWord(
            text = date,
            phrase = phrase,
            colorResId = R.color.grey
        )
        binding.inChips.chipDateToday.text = spannableString
    }
}
