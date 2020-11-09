import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime

actual fun formatDateOfYear(localDateTime: LocalDateTime): String {
    return localDateTime.toJavaLocalDateTime()
        .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}

actual fun formatTime(localDateTime: LocalDateTime): String {
    return localDateTime.toJavaLocalDateTime()
        .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
}
