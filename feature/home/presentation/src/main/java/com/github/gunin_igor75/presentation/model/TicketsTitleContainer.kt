package com.github.gunin_igor75.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TicketsTitleContainer(
    val cityFrom: String,
    val cityTo: String,
    val date: String,
    val countPassenger: String
): Parcelable