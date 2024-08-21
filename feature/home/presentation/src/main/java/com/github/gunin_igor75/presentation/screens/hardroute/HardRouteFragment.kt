package com.github.gunin_igor75.presentation.screens.hardroute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentHardRouteBinding
import com.github.gunin_igor75.presentation.screens.base.BaseHintFragment

class HardRouteFragment : BaseHintFragment() {

    private var _binding: FragmentHardRouteBinding? = null
    private val binding: FragmentHardRouteBinding
        get() = _binding?: throw IllegalStateException("FragmentHardRouteBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHardRouteBinding.inflate(inflater,container, false)
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