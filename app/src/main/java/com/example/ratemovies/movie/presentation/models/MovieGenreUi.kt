package com.example.ratemovies.movie.presentation.models

import com.example.ratemovies.movie.domain.model.MovieGenre

data class MovieGenreUi(
    val id: Int,
    val name: String,
)

fun MovieGenre.toMovieGenreUi(): MovieGenreUi {
    return MovieGenreUi(
        id = id,
        name = name,
    )
}