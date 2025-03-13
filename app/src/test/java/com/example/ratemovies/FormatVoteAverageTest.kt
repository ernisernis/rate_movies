package com.example.ratemovies

import com.example.ratemovies.core.presentation.util.formatVoteAverage
import org.junit.Test
import kotlin.test.assertEquals

class FormatVoteAverageTest {
    private val testCases = listOf(
        Pair(9.11, "9.1"),
        Pair(8.0, "8"),
        Pair(7.00, "7"),
        Pair(7.67, "7.7"),
        Pair(7.65, "7.6"),
        Pair(7.60, "7.6"),
        Pair(5.132560, "5.1"),
        Pair(-7.60, "0"),
        Pair(-3.6, "0"),
        Pair(-1.0, "0"),
        Pair(11.0, "10"),
    )

    @Test
    fun check_if_formatter_outputs_correct_values() {
        testCases.forEach { (value, expected) ->
            val actual = value.formatVoteAverage()
            assertEquals(expected, actual, "String should be $expected")
        }
    }
}