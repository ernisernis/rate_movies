package com.example.ratemovies.movie.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val id: Int,
    val release_date: String,
    val runtime: Int,
    val vote_count: Int,
)
