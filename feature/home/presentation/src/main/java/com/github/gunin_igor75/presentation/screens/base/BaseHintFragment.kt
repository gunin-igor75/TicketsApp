package com.github.gunin_igor75.presentation.screens.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

open class BaseHintFragment: Fragment() {


    fun back() {
        findNavController().popBackStack()
    }
}