package com.example.ratemovies.movie.presentation.movie_bookmark

sealed interface MovieBookmarkAction {
    data class OnMovieClick(val id: Int): MovieBookmarkAction
    data class OnBookmarkClick(val id: Int): MovieBookmarkAction
}