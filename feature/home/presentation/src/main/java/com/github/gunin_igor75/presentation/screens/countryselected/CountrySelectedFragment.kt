package com.github.gunin_igor75.presentation.screens.countryselected

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.core.common.R
import com.github.gunin_igor75.domain.model.TitleTicketModel
import com.github.gunin_igor75.presentation.adapter.CountrySelectedAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentCountrySelectedBinding
import com.github.gunin_igor75.presentation.utils.MarginItemDecorationRecommendationTickets
import com.github.gunin_igor75.presentation.utils.getDateToday
import com.github.gunin_igor75.presentation.utils.getWeek
import com.github.gunin_igor75.presentation.utils.markWord
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit


class CountrySelectedFragment : Fragment() {

    private var _binding: FragmentCountrySelectedBinding? = null
    private val binding: FragmentCountrySelectedBinding
        get() = _binding ?: throw IllegalStateException("FragmentCountrySelectedBinding is null")

    private val vm: CountrySelectedViewModel by viewModel()

    private var currentDateMillis: Long? = null

    private val adapter by lazy {
        CountrySelectedAdapter{
            launchScreenTickets()
        }
    }

    private var dateSending: Long? = null

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
        setupDateSending(
            date = Date(),
            view = binding.inChips.chipDateToday
        )
        setupListenerChips()
        swapCityFromCityTo()
        clickBack()
        clickShowAllTickets()
    }

    private fun setupTitleCityFromTo() {
        lifecycleScope.launch {
            vm.cityFromState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect{
                binding.textInputEditTextCityFrom.setText(it.cityFrom)
                binding.textInputEditTextCityTo.setText(it.cityTo)
            }
        }
    }

    private fun setupRecyclerView() {
        val divider = resources.getDimensionPixelSize(R.dimen.dp_2x)
        val dividerItemDecoration = MarginItemDecorationRecommendationTickets(divider)
        binding.inTickets.recyclerView.addItemDecoration(dividerItemDecoration)
        binding.inTickets.recyclerView.adapter = adapter

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            vm.ticketsOffers.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                adapter.items = it.data
            }
        }
    }

    private fun launchScreenTickets() {
        findNavController()
            .navigate(com.github.gunin_igor75.presentation.R.id.action_countrySelectedFragment_to_ticketsFragment)
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

    private fun setupDateSending(
        date: Date,
        view: Chip,
        changeIconVisible: Boolean = false,
    ) {
        val dateString = getDateToday(date)
        val phrase = getWeek(dateString)
        val spannableString = markWord(
            text = dateString,
            phrase = phrase,
            colorResId = R.color.grey
        )
        view.text = spannableString
        if (changeIconVisible) {
            view.isChipIconVisible = false
        }
    }

    private fun setupListenerChips() {
        binding.inChips.chipDateToday.setOnClickListener {
            launchDateDialogSending()
        }
        binding.inChips.chipBack.setOnClickListener {
            launchDateDialogBack()
        }
    }

    private fun launchDateDialogSending() {
        val min = dateSending ?: Date().time
        val max = min + PERIOD_TIME.days.toLong(DurationUnit.MILLISECONDS)
        val constraints = getCalendarConstraints(min, max)
        val datePicker = getDatePicker(constraints)

        datePicker.addOnPositiveButtonClickListener { timeInMillis ->
            currentDateMillis = timeInMillis
            dateSending = timeInMillis
            val spannableString = getSpannableString(timeInMillis)
            binding.inChips.chipDateToday.text = spannableString
        }
        datePicker.show(parentFragmentManager, TAG)
    }

    private fun launchDateDialogBack() {
        val min =
            (dateSending ?: Date().time) + START_DELTA_BACK.days.toLong(DurationUnit.MILLISECONDS)
        val max = min + PERIOD_TIME.days.toLong(DurationUnit.MILLISECONDS)
        val constraints = getCalendarConstraints(min, max)
        val datePicker = getDatePicker(constraints)

        datePicker.addOnPositiveButtonClickListener { timeInMillis ->
            val spannableString = getSpannableString(timeInMillis)
            with(binding.inChips.chipBack) {
                text = spannableString
                isChipIconVisible = false
            }
        }
        datePicker.show(parentFragmentManager, TAG)
    }

    private fun getSpannableString(timeInMillis: Long): SpannableString {
        val date = Date(timeInMillis)
        val dateString = getDateToday(date)
        val phrase = getWeek(dateString)
        val spannableString = markWord(
            text = dateString,
            phrase = phrase,
            colorResId = R.color.grey
        )
        return spannableString
    }

    private fun getDatePicker(constraints: CalendarConstraints): MaterialDatePicker<Long> {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(resources.getString(R.string.datePickerTitle))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(constraints)
            .build()
        return datePicker
    }

    private fun getCalendarConstraints(
        min: Long,
        max: Long,
    ): CalendarConstraints {
        val start = DateValidatorPointForward.from(min)
        val end = DateValidatorPointBackward.before(max)
        val dataValidator = CompositeDateValidator.allOf(listOf(start, end))
        val constrains = CalendarConstraints.Builder()
            .setValidator(dataValidator)
            .build()
        return constrains
    }

    private fun swapCityFromCityTo() {
        binding.textInputLayoutCityFrom.setEndIconOnClickListener {
            val textFrom = binding.textInputEditTextCityFrom.text.toString()
            val textTo = binding.textInputEditTextCityTo.text.toString()
            binding.textInputEditTextCityFrom.setText(textTo)
            binding.textInputEditTextCityTo.setText(textFrom)
        }
    }

    private fun clickBack() {
        binding.imageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun clickShowAllTickets() {
        binding.buttonShowAllTickets.setOnClickListener {
            val cityFrom = binding.textInputEditTextCityFrom.text.toString()
            val cityTo = binding.textInputEditTextCityTo.text.toString()
            val isValidateFrom = vm.validateCity(cityFrom)
            val isValidateTo = vm.validateCity(cityTo)
            if (isValidateFrom && isValidateTo) {
                currentDateMillis?.let {
                    val titleTicketModel = TitleTicketModel(
                        cityFrom = cityFrom,
                        cityTo = cityTo,
                        date = Date(it)
                    )
                    vm.saveCity(titleTicketModel)
                }
                launchScreenTickets()
            }
        }
    }

    private companion object {
        const val TAG = "CountrySelectedFragment"
        const val PERIOD_TIME = 45
        const val START_DELTA_BACK = 3
    }
}
