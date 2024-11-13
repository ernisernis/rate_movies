package com.example.ratemovies.movie.domain

data class MovieDetails(
    val id: Int,
    val releaseDate: String,
    val runtime: Int,
    val voteCount: Int,
)