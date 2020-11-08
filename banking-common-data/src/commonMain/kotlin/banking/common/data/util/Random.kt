package banking.common.data.util

class Random {
    companion object {
        fun double(): Double {
            return (0..10).random() / 10.0
        }
        fun string(length: Int = 12): String {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
        }
    }
}
