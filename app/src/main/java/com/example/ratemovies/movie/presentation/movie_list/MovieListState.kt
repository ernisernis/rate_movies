package com.example.ratemovies.movie.presentation.movie_list

import com.example.ratemovies.movie.presentation.models.MovieUi

data class MovieListState(
    val moviesUi: List<MovieUi> = emptyList()
)
