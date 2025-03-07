package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.core.presentation.util.formatVoteAverage
import com.example.ratemovies.movie.data.dto.MovieDto
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.Rating
import com.example.ratemovies.movie.presentation.models.toDisplayableRuntime
import com.example.ratemovies.movie.presentation.movie_detail.defaultMovieDetail

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        adult = adult,
        backdropPath = backdrop_path,
        genreIds = genre_ids,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        releaseDate = release_date,
        video = video,
        voteAverage = vote_average,
        voteCount = vote_count,
        movieDetail = defaultMovieDetail,
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