package com.example.ratemovies.movie.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)