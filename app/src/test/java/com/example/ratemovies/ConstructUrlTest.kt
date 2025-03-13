package com.example.ratemovies

import com.example.ratemovies.BuildConfig.BASE_URL
import com.example.ratemovies.core.data.networking.constructUrl
import org.junit.Test
import kotlin.test.assertEquals

class ConstructUrlTest {

    private val testCases = listOf(
        Pair(BASE_URL, BASE_URL),
        Pair("/movies", "${BASE_URL}movies"),
        Pair("movies", "${BASE_URL}movies"),
        Pair("/movies/31/abc", "${BASE_URL}movies/31/abc"),
        Pair("31movies", "${BASE_URL}31movies"),
        Pair("", BASE_URL),
    )

    @Test
    fun the_string_should_be_constructed_properly_to_include_base_URL() {
        testCases.forEach { (value, expected) ->
            val actual = constructUrl(value)

            assertEquals(expected, actual, "constructed URL should be $expected")
        }
    }
}