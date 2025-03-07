package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.core.presentation.util.getImageUrl
import com.example.ratemovies.core.presentation.util.getReleaseYear
import com.example.ratemovies.movie.data.database.entities.BookmarkEntity
import com.example.ratemovies.movie.domain.model.BookmarkMovie
import com.example.ratemovies.movie.presentation.models.BookmarkMovieUi

fun BookmarkEntity.toBookmarkMovie(): BookmarkMovie {
    return BookmarkMovie(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        creationTime = creationTime,
    )
}

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