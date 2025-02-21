package com.example.ratemovies.movie.presentation.movie_bookmark

sealed interface MovieBookmarkAction {
    data object OnClick: MovieBookmarkAction
}