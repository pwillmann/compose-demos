package banking.common.compose.util

import formatDateOfYear
import formatTime
import kotlinx.datetime.LocalDateTime

fun LocalDateTime.printTime(): String {
    return formatTime(this)
}

fun LocalDateTime.printDateOfYear(): String {
    return formatDateOfYear(this)
}
