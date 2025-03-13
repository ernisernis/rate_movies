package com.example.ratemovies.movie.presentation.movie_list

import com.example.ratemovies.movie.domain.model.Movie

data class MovieListState(
    val loadingState: LoadingState = LoadingState.Loading,
    val nowPlayingMoviesUi: List<Movie> = emptyList(),
    val popularMoviesUi: List<Movie> = emptyList(),
    val topRatedMoviesUi: List<Movie> = emptyList(),
    val upcomingMoviesUi: List<Movie> = emptyList(),
)

sealed class LoadingState {
    data object Loading: LoadingState()
    data object Success: LoadingState()
    data class Error(val message: String): LoadingState()
}