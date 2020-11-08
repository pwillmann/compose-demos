package pwillmann.banking.android.ui.common.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date

fun formatDate(date: Date, formatPattern: String): String {
    val localDateTime = localDateTimeFromDate(date)
    return localDateTime.format(DateTimeFormatter.ofPattern(formatPattern))
}

fun formatDateOfYear(localDateTime: LocalDateTime): String {
    return localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}

fun formatTime(date: Date): String {
    return formatTime(localDateTimeFromDate(date))
}

fun Date.printTime(): String {
    return formatTime(this)
}
fun LocalDateTime.printTime(): String {
    return formatTime(this)
}
fun formatTime(localDateTime: LocalDateTime): String {
    return localDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
}

fun localDateTimeFromDate(date: Date): LocalDateTime =
    LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
