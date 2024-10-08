package com.github.gunin_igor75.presentation.screens.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.core.common.R
import com.core.common.utils.Constants.Companion.MESSAGE_ERROR
import com.core.common.utils.KeyBoardFilter
import com.core.common.utils.Utils
import com.github.gunin_igor75.presentation.adapter.OffersAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentOffersBinding
import com.github.gunin_igor75.presentation.utils.MarginItemDecorationOffers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersFragment : Fragment() {

    private var _binding: FragmentOffersBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentOffersBinding is null")

    private val vm: OffersTextEditViewModel by viewModel()

    private val filter = KeyBoardFilter()

    private val adapterOffers by lazy {
        OffersAdapter()
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
        setupValueEditTextCityFrom()
        setupValidationEditTextCityFrom()
        setupRecyclerView()
        observeOffers()
        observeError()
        setupIsEnabledView()
        launchScreenDestination()
    }

    private fun setupRecyclerView() {
        with(binding) {
            val divider = resources.getDimensionPixelSize(R.dimen.dp_8x)
            val dividerItemDecoration = MarginItemDecorationOffers(divider)
            recyclerView.addItemDecoration(dividerItemDecoration)
            recyclerView.adapter = adapterOffers
        }
    }

    private fun observeOffers() {
        lifecycleScope.launch {
            vm.offersState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                it.data?.let { list ->
                    adapterOffers.items = list
                }
            }
        }
    }

    private fun observeError() {
        lifecycleScope.launch {
            vm.error.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { isError ->
                if (isError) {
                    Utils.showAlertDialog(
                        context = requireContext(),
                        message = MESSAGE_ERROR
                    )
                }
            }
        }
    }

    private fun setupValueEditTextCityFrom() {
        binding.inInputCity.textInputEditTextCityFrom.filters = arrayOf(filter)
        binding.inInputCity.tetInputEditTextCityTo.filters = arrayOf(filter)
        lifecycleScope.launch {
            vm.cityFromState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                binding.inInputCity.textInputEditTextCityFrom.setText(it.cityFrom)
            }
        }
    }

    private fun setupValidationEditTextCityFrom() {
        binding.inInputCity.textInputEditTextCityFrom.addTextChangedListener {
            val city = it.toString()
            vm.validate(city)
        }
    }

    private fun setupIsEnabledView() {
        lifecycleScope.launch {
            vm.isValidateCity.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { isValidate ->
                    binding.inInputCity.imageView.isEnabled = isValidate
                    binding.inInputCity.tetInputEditTextCityTo.isEnabled = isValidate
                }
        }
    }

    private fun launchScreenDestination() {
        binding.inInputCity.imageView.setOnClickListener {
            val city = binding.inInputCity.textInputEditTextCityFrom.text.toString()
            saveCityFrom(city)
            navigateToScreenDestination()
        }
        binding.inInputCity.tetInputEditTextCityTo.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val city = binding.inInputCity.textInputEditTextCityFrom.text.toString()
                view.clearFocus()
                saveCityFrom(city)
                navigateToScreenDestination()
            }
        }
    }

    private fun navigateToScreenDestination() {
        findNavController().navigate(
            com.github.gunin_igor75.presentation.R.id.action_OffersFragment_to_findCountryFragment
        )
    }
    private fun saveCityFrom(city: String) {
        vm.saveCity(city)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}