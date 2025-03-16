package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.core.presentation.util.formatVoteAverage
import com.example.ratemovies.movie.data.database.entities.BookmarkEntity
import com.example.ratemovies.movie.data.database.entities.MovieEntity
import com.example.ratemovies.movie.data.dto.MovieDto
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.Rating
import com.example.ratemovies.movie.presentation.models.toDisplayableRuntime

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        movieDetail = null,
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        movieDetailDto = movieDetail?.toMovieDetailDto(),
    )
}

fun Movie.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = movieDetail?.runtime?.toDisplayableRuntime()?.formatted,
        voteAverage = voteAverage.formatVoteAverage(),
        creationTime = System.currentTimeMillis()
    )
}

fun MovieEntity.toMovie(): Movie{
    return Movie(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdropPath?: "",
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        movieDetail = movieDetailDto?.toMovieDetail(),
    )
}

fun Movie.toRating(): Rating {
    return Rating(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = movieDetail?.runtime?.toDisplayableRuntime()?.formatted,
        voteAverage = voteAverage.formatVoteAverage(),
        description = null,
        userRating = 0,
        creationTime = System.currentTimeMillis(),
    )
}