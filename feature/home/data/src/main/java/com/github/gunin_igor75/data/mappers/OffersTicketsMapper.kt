package com.github.gunin_igor75.data.mappers

import com.core.network.dto.OffersContainer
import com.core.network.dto.TicketsContainer
import com.core.network.dto.TicketsOffersContainer
import com.github.gunin_igor75.domain.model.Offer
import com.github.gunin_igor75.domain.model.Ticket
import com.github.gunin_igor75.domain.model.TicketsOffers
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val PATTERN_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss"
private const val PATTERN_HOUR_MINUTE = "HH:mm"
private const val HOUR_TO_MILLIS = 3600000

fun List<OffersContainer.OfferDto>.toOffers() = map { it.toOffer() }

fun List<TicketsOffersContainer.TicketOfferDto>.toTicketsOffers() = map { it.toTicketsOffers() }

fun List<TicketsContainer.TicketDto>.toTickets() = map { it.toTicket() }

private fun OffersContainer.OfferDto.toOffer() = Offer(
    id = id,
    title = title,
    town = town,
    price = price.value.toString().toCountRuble()
)

private fun TicketsOffersContainer.TicketOfferDto.toTicketsOffers() = TicketsOffers(
    id = id,
    title = title,
    timeRange = time_range.joinToString(" "),
    price = price.value.toString().toCountRuble()
)

private fun TicketsContainer.TicketDto.toTicket() = Ticket(
    id = id,
    badge = badge,
    timeDeparture = getHourAndMinute(departure.date),
    timeArrival = getHourAndMinute(arrival.date),
    timeFlight = getHourDestination(departure.date, arrival.date),
    transfer = has_transfer,
    airportArrival = arrival.airport,
    airportDeparture = departure.airport
)

private fun String.toCountRuble() = buildString {
    var begin: Int
    var end = this@toCountRuble.length
    while (end > 2) {
        begin = end - 3
        this.insert(0, " " + this@toCountRuble.substring(begin, end))
        end = begin
    }
    if (end != 0) {
        this.insert(0, this@toCountRuble.substring(0, end))
    }
    this.append("\u20BD")
}

fun getHourAndMinute(time: String): String {
    val calendar = Calendar.getInstance()
    val calendarFormat = getCalendarFormat(calendar, time)
    val format = SimpleDateFormat(PATTERN_HOUR_MINUTE, Locale.getDefault())
    return format.format(calendarFormat.time)
}

fun getHourDestination(time1: String, time2: String): String {
    val calendar = Calendar.getInstance()
    val millis1 = getCalendarFormat(calendar, time1).timeInMillis
    val millis2 = getCalendarFormat(calendar, time2).timeInMillis
    calendar.timeInMillis = millis2 - millis1
    val result = calendar.timeInMillis.toDouble() / HOUR_TO_MILLIS
    return getFormatNumber(result)
}

fun getCalendarFormat(calendar: Calendar, time: String): Calendar {
    val format = SimpleDateFormat(PATTERN_DATE_TIME, Locale.getDefault())
    calendar.time = format.parse(time)
        ?: throw IllegalArgumentException("Time ticket is not supported by format")
    return calendar
}

fun getFormatNumber(value: Double): String {
    val decimalFormat = DecimalFormatSymbols.getInstance()
    decimalFormat.decimalSeparator = '.'
    return DecimalFormat("0.0", decimalFormat).format(value)
}

fun main() {
    val date = "2024-02-23T03:15:00"
    val date1 = "2024-02-23T07:10:00"
    val hour = getHourDestination(date, date1)
    println(hour)
    println(getHourAndMinute(date))
}




