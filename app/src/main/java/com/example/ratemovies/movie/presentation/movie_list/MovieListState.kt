package com.example.ratemovies.movie.presentation.movie_list

import com.example.ratemovies.movie.domain.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val nowPlayingMoviesUi: List<Movie> = emptyList(),
    val popularMoviesUi: List<Movie> = emptyList(),
    val topRatedMoviesUi: List<Movie> = emptyList(),
    val upcomingMoviesUi: List<Movie> = emptyList(),
)