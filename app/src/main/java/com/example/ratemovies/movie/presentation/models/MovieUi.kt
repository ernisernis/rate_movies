package com.example.ratemovies.movie.presentation.models

data class MovieUi(
    val title: String,
    val overview: String,
    val imageUrl: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: String,
    val popularity: String,
)
