package pwillmann.banking.android.ui.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun formatDateOfYear(localDateTime: LocalDateTime): String {
    return localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}

fun LocalDateTime.printTime(): String {
    return formatTime(this)
}
fun formatTime(localDateTime: LocalDateTime): String {
    return localDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
}
