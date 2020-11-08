package com.pwillmann.composedemos.banking.util

fun Double.print(): String {
    return String.format("$ %.1f", this)
}

fun Double.printNoDecimals(): String {
    return String.format("$ %.0f", this)
}
