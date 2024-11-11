package com.example.ratemovies.movie.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    val results: List<MovieDto>,
)