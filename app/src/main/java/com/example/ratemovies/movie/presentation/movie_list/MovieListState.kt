package com.example.ratemovies.movie.presentation.movie_list

import com.example.ratemovies.movie.presentation.models.MovieUi

data class MovieListState(
    val isLoading: Boolean = false,
    val nowPlayingMoviesUi: List<MovieUi> = emptyList(),
    val popularMoviesUi: List<MovieUi> = emptyList(),
    val topRatedMoviesUi: List<MovieUi> = emptyList(),
    val upcomingMoviesUi: List<MovieUi> = emptyList(),
)