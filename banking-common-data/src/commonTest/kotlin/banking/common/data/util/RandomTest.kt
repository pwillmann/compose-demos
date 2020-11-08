package banking.common.data.util

import kotlin.test.Test
import kotlin.test.assertTrue

class RandomTest {

    @Test
    fun `test random double upper and lower bounds`() {
        for (i in 0..100000) {
            val randomDouble = Random.double()
            assertTrue(randomDouble >= 0.0)
            assertTrue(randomDouble <= 1.0)
        }
    }
}
