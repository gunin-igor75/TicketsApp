package com.github.gunin_igor75.presentation.screens.weekend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.gunin_igor75.presentation.databinding.FragmentWeekendBinding
import com.github.gunin_igor75.presentation.screens.base.BaseHintFragment


class WeekendFragment : BaseHintFragment() {

    private var _binding: FragmentWeekendBinding? = null
    private val binding: FragmentWeekendBinding
        get() = _binding?: throw IllegalStateException("FragmentWeekendBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentWeekendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            back()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}