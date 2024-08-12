package com.github.gunin_igor75.domain.model

import com.core.common.utils.Constants.Companion.TICKET_ID
import java.util.Date

data class TicketModel(
    val id: String = TICKET_ID,
    val cityFrom: String? = null,
    val cityTo: String? = null,
    val date: Date? = null
)
