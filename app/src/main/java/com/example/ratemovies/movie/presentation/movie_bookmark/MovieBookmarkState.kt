package com.example.ratemovies.movie.presentation.movie_bookmark

import com.example.ratemovies.movie.domain.model.BookmarkMovie

data class MovieBookmarkState(
    val bookmarkMovies: List<BookmarkMovie> = listOf(),
)