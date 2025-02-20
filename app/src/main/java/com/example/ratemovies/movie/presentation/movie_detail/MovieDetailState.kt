package com.example.ratemovies.movie.presentation.movie_detail

import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.presentation.models.MovieUi

data class MovieDetailState(
    val movie: Movie? = null,
    val movieUi: MovieUi? = null,
    val isBookmarked: Boolean = false,
)
