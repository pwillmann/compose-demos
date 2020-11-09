package banking.common.compose.util

import kotlin.math.pow
import kotlin.math.roundToInt

fun Double.print(): String {
    val integerDigits = this.toInt()
    val floatDigits = ((this - integerDigits) * 10f.pow(2)).roundToInt()
    return "$integerDigits.$floatDigits"
}

fun Double.printNoDecimals(): String {
    return this.toInt().toString()
}
