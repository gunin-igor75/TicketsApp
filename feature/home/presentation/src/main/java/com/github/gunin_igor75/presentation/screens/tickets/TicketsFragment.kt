package com.github.gunin_igor75.presentation.screens.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.FragmentTiketsBinding

class TicketsFragment : Fragment() {

    private var _binding: FragmentTiketsBinding? = null
    private val binding: FragmentTiketsBinding
        get() = _binding ?: throw IllegalStateException("FragmentTiketsBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tikets, container, false)
    }
}