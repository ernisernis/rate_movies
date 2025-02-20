package com.example.ratemovies.movie.presentation.movie_rate

import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.presentation.models.MovieUi

data class MovieRateState(
    val movie: Movie? = null,
    val movieUi: MovieUi? = null,
    val selectedIndex: Int = 0,
    val description: String = "",
    val indexError: String? = null,
    val descriptionError: String? = null,
    val popBackStackFlag: Boolean = false,
)
