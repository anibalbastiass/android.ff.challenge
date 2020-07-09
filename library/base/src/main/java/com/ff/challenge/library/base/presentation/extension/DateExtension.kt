package com.ff.challenge.library.base.presentation.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.DateTimeException
import java.util.Date
import java.util.Locale
import java.util.TimeZone

private const val DD_MM_YYYY_FORMAT = "yyyy-MM-dd"
private const val MMM_DD_YYYY_FORMAT = "MMM dd, yyyy"

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDate(): String {
    var formattedDate = ""
    try {
        var format = SimpleDateFormat(DD_MM_YYYY_FORMAT, Locale.ENGLISH)
        format.timeZone = TimeZone.getTimeZone("UTC")
        var newDate: Date? = null
        try {
            newDate = format.parse(this)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        format = SimpleDateFormat(MMM_DD_YYYY_FORMAT, Locale.ENGLISH)
        format.timeZone = TimeZone.getDefault()

        formattedDate = format.format(newDate)
    } catch (e: DateTimeException) {
        e.printStackTrace()
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