package com.example.ratemovies.movie.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val id: Int,
    val release_date: String,
    val runtime: Int,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<MovieGenreDto>,
    val overview: String,
    val credits: CreditsResponseDto,
)
