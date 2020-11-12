package challenge

import kotlin.system.measureTimeMillis

fun solution(n: Int): Int {
    return n
}

fun main() {
    verify("true") { "true" }
    verify(true) { true }
    verify(false) { "false" }
    verify(null) { null }
}

private var testNo = 1
fun verify(expected: Any?, actualBlock: () -> Any?) {
    var actual: Any? = null
    val elapsed = measureTimeMillis {
        actual = actualBlock()
    }

    val list = mutableListOf<Int>().toIntArray()
    val isSuccess = (expected == null && actual == null) || expected?.equals(actual) == true
    println("\nTest #$testNo: ${if (isSuccess) "OK" else "FAIL"}\nExp: $expected\nAct: $actual\nrunning for $elapsed ms\n")
    testNo++
}
