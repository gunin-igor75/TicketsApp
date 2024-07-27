package com.github.gunin_igor75.presentation.screens.hottickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.gunin_igor75.presentation.databinding.FragmentHotTicketsBinding
import com.github.gunin_igor75.presentation.screens.base.BaseHintFragment


class HotTicketsFragment : BaseHintFragment() {

    private var _binding: FragmentHotTicketsBinding? = null
    private val binding: FragmentHotTicketsBinding
        get() = _binding ?: throw IllegalStateException("FragmentHotTicketsBinding is nul")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHotTicketsBinding.inflate(inflater, container, false)
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