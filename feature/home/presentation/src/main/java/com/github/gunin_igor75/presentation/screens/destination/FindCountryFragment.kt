package com.github.gunin_igor75.presentation.screens.destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.core.common.R
import com.core.common.utils.KeyBoardFilter
import com.github.gunin_igor75.presentation.adapter.CountryItemAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentFindBinding
import com.github.gunin_igor75.presentation.utils.MarginItemDecorationFind
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindCountryFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentFindBinding? = null
    private val binding: FragmentFindBinding
        get() = _binding?: throw IllegalStateException("FragmentFindBinding is null")

    private val args by navArgs<FindCountryFragmentArgs>()

    private val vm: FindCountryViewModel by viewModel()

    private val filter = KeyBoardFilter()

    private val adapterCountry by lazy {
        CountryItemAdapter(
            onClickCity = ::setupCityTo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFullScreenBottomSheet()
        setupValueEditTextCityFrom()
        setupTextInputCityFrom()
        setupRecyclerView()
        observeViewModel()
        launchScreensHnt()
        onClickStartIconCityTo()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            vm.countryItems().flowWithLifecycle(viewLifecycleOwner.lifecycle).collectLatest {
                adapterCountry.items = it
            }
        }
    }

    private fun setupCityTo(@StringRes cityResId: Int) {
        binding.includeInputCity.tetInputEditTextCityTo.setText(cityResId)
    }

    private fun setupFullScreenBottomSheet() {
        val bottomSheet: FrameLayout =
            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
                ?: throw IllegalStateException("com.google.android.material.R.id.design_bottom_sheet not found")
        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        val behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.apply {
            peekHeight = resources.displayMetrics.heightPixels
            state = BottomSheetBehavior.STATE_EXPANDED
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    private fun setupTextInputCityFrom() {
        val city = args.cityFrom
        binding.includeInputCity.textInputEditTextCityFrom.setText(city)
    }

    private fun setupRecyclerView() {
        with(binding) {
            val divider = resources.getDimensionPixelSize(R.dimen.dp_1x)
            val dividerItemDecoration = MarginItemDecorationFind(divider)
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.adapter = adapterCountry
        }
    }


    private fun setupValueEditTextCityFrom() {
        binding.includeInputCity.textInputEditTextCityFrom.filters = arrayOf(filter)
        binding.includeInputCity.tetInputEditTextCityTo.filters = arrayOf(filter)
    }

    private fun launchScreensHnt() {
        with(binding.includeHintFind) {
            cardChoiceViewHardRoute.setListener {
                findNavController().navigate(com.github.gunin_igor75.presentation.R.id.action_findCountryFragment_to_hardRouteFragment)
            }
            cardChoiceViewWeekend.setListener {
                findNavController().navigate(com.github.gunin_igor75.presentation.R.id.action_findCountryFragment_to_weekendFragment)
            }
            cardChoiceViewHotTickets.setListener {
                findNavController().navigate(com.github.gunin_igor75.presentation.R.id.action_findCountryFragment_to_hotTicketsFragment)
            }
            cardChoiceViewAnyWhere.setListener {
                binding.includeInputCity.tetInputEditTextCityTo.setText(R.string.sochi)
            }
        }
    }

    private fun onClickStartIconCityTo() {
        binding.includeInputCity.textInputLayoutCityTo.setStartIconOnClickListener {
            val cityTo = binding.includeInputCity.tetInputEditTextCityTo.text.toString()
            val isValidate = vm.validateCity(cityTo)
            if (isValidate) {
                val cityFrom = binding.includeInputCity.textInputEditTextCityFrom.text.toString()
                findNavController().navigate(FindCountryFragmentDirections.actionFindCountryFragmentToCountrySelectedFragment(
                    cityFrom = cityFrom,
                    cityTo = cityTo
                ))
            }
        }
    }
}
