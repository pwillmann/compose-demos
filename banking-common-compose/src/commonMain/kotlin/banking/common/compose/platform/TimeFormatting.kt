import kotlinx.datetime.LocalDateTime

expect fun formatDateOfYear(localDateTime: LocalDateTime): String

expect fun formatTime(localDateTime: LocalDateTime): String
