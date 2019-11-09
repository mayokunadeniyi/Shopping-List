package com.mayokun.shoppinglist.utils


import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Created by Mayokun Adeniyi on 2019-11-09.
 */

/**
 * This gets the current system time and returns a formatted String
 * representing the time the Shopping Item was created
 */
fun getCurrentSystemTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    return current.format(formatter)
}
