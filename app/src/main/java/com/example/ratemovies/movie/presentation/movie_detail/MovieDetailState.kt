package com.example.ratemovies.movie.presentation.movie_detail

import com.example.ratemovies.movie.presentation.models.MovieUi
import com.example.ratemovies.movie.presentation.models.defaultMovieUi

data class MovieDetailState(
    val movieUi: MovieUi = defaultMovieUi(),
)
