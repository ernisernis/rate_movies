package com.example.ratemovies.movie.data.database.converters

import androidx.room.TypeConverter
import com.example.ratemovies.movie.data.dto.CastDto
import com.example.ratemovies.movie.data.dto.CrewDto
import com.example.ratemovies.movie.data.dto.MovieDetailDto
import com.example.ratemovies.movie.data.dto.MovieGenreDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object MovieTypeConverter {

    @TypeConverter
    fun movieGenreDtoFromString(value: String): List<MovieGenreDto> {
        return Json.decodeFromString<List<MovieGenreDto>>(value)
    }

    @TypeConverter
    fun movieGenreDtoFromList(value: List<MovieGenreDto>): String {
        return Json.encodeToString(value)
    }


    @TypeConverter
    fun movieCastDtoFromString(value: String): List<CastDto> {
        return Json.decodeFromString<List<CastDto>>(value)
    }

    @TypeConverter
    fun movieCastDtoFromList(value: List<CastDto>): String {
        return Json.encodeToString(value)
    }


    @TypeConverter
    fun movieCrewFromString(value: String): List<CrewDto> {
        return Json.decodeFromString<List<CrewDto>>(value)
    }

    @TypeConverter
    fun movieCrewFromList(value: List<CrewDto>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun movieDetailFromString(value: String): MovieDetailDto {
        return Json.decodeFromString<MovieDetailDto>(value)
    }

    @TypeConverter
    fun movieDetailFromObject(value: MovieDetailDto): String {
        return Json.encodeToString(value)
    }
}
