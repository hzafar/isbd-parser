package ca.voidstarzero.isbd

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ParserUtilsTest {

    @Test
    fun noCombinations() {
        val combos = periodCombinations(emptyList())

        assertEquals(1, combos.size)
        assertTrue(combos.contains(emptyList()))
    }

    @Test
    fun oneCombination() {
        val combos = periodCombinations(listOf(1))

        assertEquals(2, combos.size)
        assertTrue(combos.contains(emptyList()))
        assertTrue(combos.contains(listOf(1)))
    }

    @Test
    fun manyCombinations() {
        val combos = periodCombinations(listOf(1, 2, 3, 4))

        assertEquals(16, combos.size)
        assertTrue(combos.contains(emptyList()))
        assertTrue(combos.contains(listOf(1)))
        assertTrue(combos.contains(listOf(2)))
        assertTrue(combos.contains(listOf(3)))
        assertTrue(combos.contains(listOf(4)))
        assertTrue(combos.contains(listOf(2, 1)))
        assertTrue(combos.contains(listOf(3, 1)))
        assertTrue(combos.contains(listOf(4, 1)))
        assertTrue(combos.contains(listOf(3, 2)))
        assertTrue(combos.contains(listOf(4, 2)))
        assertTrue(combos.contains(listOf(4, 3)))
        assertTrue(combos.contains(listOf(3, 2, 1)))
        assertTrue(combos.contains(listOf(4, 2, 1)))
        assertTrue(combos.contains(listOf(4, 3, 1)))
        assertTrue(combos.contains(listOf(4, 3, 2)))
        assertTrue(combos.contains(listOf(4, 3, 2, 1)))
    }
}