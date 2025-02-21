package com.example.ratemovies.movie.presentation.models

import com.example.ratemovies.core.presentation.util.getImageUrl
import com.example.ratemovies.core.presentation.util.getReleaseYear
import com.example.ratemovies.movie.domain.BookmarkMovie

data class BookmarkMovieUi(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseYear: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
)

fun BookmarkMovie.toBookmarkMovieUi(): BookmarkMovieUi {
    return BookmarkMovieUi(
        id = id,
        imageUrl = posterPath.getImageUrl(),
        title = title,
        releaseYear = releaseDate.getReleaseYear(),
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage
    )
}
