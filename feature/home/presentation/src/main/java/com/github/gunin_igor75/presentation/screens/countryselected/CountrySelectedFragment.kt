package com.github.gunin_igor75.presentation.screens.countryselected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.gunin_igor75.presentation.databinding.FragmentCountrySelectedBinding


class CountrySelectedFragment : Fragment() {

    private var _binding: FragmentCountrySelectedBinding? = null
    private val binding: FragmentCountrySelectedBinding
        get() = _binding ?: throw IllegalStateException("FragmentCountrySelectedBinding is null")

    private val args by navArgs<CountrySelectedFragmentArgs>()

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
    }

    private fun setupTitleCityFromTo() {
        binding.textInputEditTextCityFrom.setText(args.cityFrom)
        binding.textInputEditTextCityTo.setText(args.cityTo)
    }
}