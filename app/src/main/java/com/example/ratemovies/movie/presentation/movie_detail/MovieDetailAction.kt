package com.example.ratemovies.movie.presentation.movie_detail

import com.example.ratemovies.movie.domain.model.Movie

interface MovieDetailAction {
    data object OnRateClick: MovieDetailAction
    data class OnSelectedMovieChange(val movie: Movie): MovieDetailAction
    data object OnBackClick: MovieDetailAction
}