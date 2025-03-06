package com.example.ratemovies.movie.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object IntListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return Json.encodeToString(list)
    }
}