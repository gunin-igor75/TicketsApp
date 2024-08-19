package com.github.gunin_igor75.presentation.mappers

import com.github.gunin_igor75.domain.model.Ticket
import com.github.gunin_igor75.domain.model.TitleTicketModel
import com.github.gunin_igor75.presentation.model.UiTicket
import com.github.gunin_igor75.presentation.model.UiTitleTicket
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private const val PATTERN_DATE_TIME = "dd MMMM"

fun TitleTicketModel.toUiTitleTicket(): UiTitleTicket {
    val currentDate = date ?: throw IllegalStateException("Date titleTicketModel is null")
    return UiTitleTicket(
        title = "$cityFrom-$cityTo",
        description = getDescriptionTicket(currentDate)
    )
}


fun List<Ticket>.toUiTickets() = map { it.toUiTicket() }

fun Ticket.toUiTicket() = UiTicket(
    id,
    price,
    badge,
    timeDeparture,
    timeArrival,
    timeFlight,
    transfer,
    airportArrival,
    airportDeparture
)

private fun getDescriptionTicket(date: Date): String {
    val format = SimpleDateFormat(PATTERN_DATE_TIME, Locale.getDefault())
    val dayMoth = format.format(date)
    return "$dayMoth, 1 пвссажир"
}

