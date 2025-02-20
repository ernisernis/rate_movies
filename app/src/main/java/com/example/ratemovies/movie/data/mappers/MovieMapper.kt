package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.movie.data.dto.MovieDto
import com.example.ratemovies.movie.domain.Movie
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