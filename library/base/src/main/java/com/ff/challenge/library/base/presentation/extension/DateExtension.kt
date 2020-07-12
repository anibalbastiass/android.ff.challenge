package com.ff.challenge.library.base.presentation.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val MMM_DD_YYYY_FORMAT = "dd MMM, yyyy"

fun String.formatDate(): String {
    var formattedDate = ""
    try {
        var format = SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS_Z, Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        var newDate: Date? = null
        try {
            newDate = format.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        format = SimpleDateFormat(MMM_DD_YYYY_FORMAT, Locale.getDefault())
        format.timeZone = TimeZone.getDefault()

        formattedDate = format.format(newDate)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return formattedDate
}

fun Int.minutesToHours(): String {
    val time = this
    val hours: Int = time / 60
    val minutes: Int = time % 60
    return String.format("%d hr %d mins", hours, minutes)
}