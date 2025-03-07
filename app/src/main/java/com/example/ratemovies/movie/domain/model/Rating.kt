package com.example.ratemovies.movie.domain.model

data class Rating(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
    val description: String?,
    val userRating: Int,
    val creationTime: Long,
)