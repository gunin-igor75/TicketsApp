package com.github.gunin_igor75.presentation.utils

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


private const val PATTERN = "dd MMM EEE"


fun getDateToday(date: Date): String {
    val format = SimpleDateFormat(PATTERN, Locale.getDefault())
    val result = format.format(date).replace('.', ',')
    return result
}

fun getWeek(word: String): String {
    val data = word.split(" ")
    return data[data.size -1]
}

fun Fragment.markWord(
    text: String,
    phrase: String,
    @ColorRes colorResId: Int,
): SpannableString {
    val spannableString = SpannableString(text)
    val start = text.indexOf(phrase)
    val end = start + phrase.length
    spannableString.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(requireContext(), colorResId)),
        start,
        end,
        Spanned.SPAN_EXCLUSIVE_INCLUSIVE
    )
    return spannableString
}


fun main() {

}