package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.movie.data.networking.dto.MovieDetailsDto
import com.example.ratemovies.movie.domain.MovieDetails

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        releaseDate = release_date,
        runtime = runtime,
        voteAverage = vote_average,
    )
}