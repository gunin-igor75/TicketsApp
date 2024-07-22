package com.github.gunin_igor75.presentation.screens.destination

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.gunin_igor75.presentation.databinding.FragmentFindBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FindCountryFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentFindBinding? = null
    private val binding: FragmentFindBinding
        get() = _binding?: throw IllegalStateException("FragmentFindBinding is null")

    private val args by navArgs<FindCountryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = args.cityFrom
        val  navController = findNavController()
    }
}