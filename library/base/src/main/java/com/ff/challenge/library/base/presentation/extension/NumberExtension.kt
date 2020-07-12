package com.ff.challenge.library.base.presentation.extension

import java.math.RoundingMode
import java.text.DecimalFormat

fun Int.applyMilesSeparator(): String {
    return String.format("%,d", this)
}

fun Double.applyMilesSeparator(): String {
    val df = DecimalFormat("#,###.##")
    df.toLocalizedPattern()
    df.roundingMode = RoundingMode.CEILING
    return df.format(this)
}
