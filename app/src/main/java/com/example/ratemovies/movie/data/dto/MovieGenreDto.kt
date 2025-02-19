package com.example.ratemovies.movie.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MovieGenreDto(
    val id: Int,
    val name: String,
)
