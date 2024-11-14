package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.movie.data.networking.dto.MovieDetailsDto
import com.example.ratemovies.movie.data.networking.dto.MovieGenreDto
import com.example.ratemovies.movie.domain.MovieDetails
import com.example.ratemovies.movie.domain.MovieGenre

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        releaseDate = release_date,
        runtime = runtime,
        voteAverage = vote_average,
        genres = genres.toMovieGenreList(),
        overview = overview,
    )
}

fun List<MovieGenreDto>.toMovieGenreList(): List<MovieGenre> {
    return this.map {
        MovieGenre(
            id = it.id,
            name = it.name,
        )
    }
}