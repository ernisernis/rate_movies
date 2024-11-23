package com.example.ratemovies.movie.presentation.movie_rate

sealed interface MovieRateAction {
    data class OnRateClick(val index: Int) : MovieRateAction

    object OnRateSubmit : MovieRateAction
}