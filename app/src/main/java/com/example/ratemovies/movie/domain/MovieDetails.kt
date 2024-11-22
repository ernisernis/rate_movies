package com.example.ratemovies.movie.domain

data class MovieDetails(
    val id: Int,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<MovieGenre>,
    val overview: String,
    val cast: List<Cast>,
    val crew: List<Crew>,
)