package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.movie.data.dto.CastDto
import com.example.ratemovies.movie.data.dto.CreditsResponseDto
import com.example.ratemovies.movie.data.dto.CrewDto
import com.example.ratemovies.movie.data.dto.MovieDetailDto
import com.example.ratemovies.movie.data.dto.MovieGenreDto
import com.example.ratemovies.movie.domain.model.Cast
import com.example.ratemovies.movie.domain.model.CreditsResponse
import com.example.ratemovies.movie.domain.model.Crew
import com.example.ratemovies.movie.domain.model.MovieDetail
import com.example.ratemovies.movie.domain.model.MovieGenre

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genres = genres.map { it.toMovieGenre() },
        overview = overview,
        credits = credits.toCreditsResponse(),
    )
}

fun CreditsResponseDto.toCreditsResponse(): CreditsResponse {
    return CreditsResponse (
        cast = cast.map { it.toCast() },
        crew = crew.map { it.toCrew() },
    )
}

fun MovieGenreDto.toMovieGenre(): MovieGenre {
    return MovieGenre(
        id = id,
        name = name
    )
}

fun CastDto.toCast(): Cast {
    return Cast(
        id = id,
        name = name,
        profilePath = profilePath,
        character = character,
    )
}

fun CrewDto.toCrew(): Crew {
    return Crew(
        id = id,
        name = name,
        job = job,
        profilePath = profilePath ?: "",
    )
}

fun MovieGenre.toMovieGenreDto(): MovieGenreDto {
    return MovieGenreDto(
        id = id,
        name = name
    )
}

fun CreditsResponse.toCreditsResponseDto(): CreditsResponseDto {
    return CreditsResponseDto(
        cast = cast.map { it.toCastDto() },
        crew = crew.map { it.toCrewDto() }
    )
}

fun Cast.toCastDto(): CastDto {
    return CastDto(
        id = id,
        name = name,
        profilePath = profilePath,
        character = character,
    )
}

fun Crew.toCrewDto(): CrewDto {
    return CrewDto(
        id = id,
        name = name,
        job = job,
        profilePath = profilePath
    )
}

fun MovieDetail.toMovieDetailDto(): MovieDetailDto {
    return MovieDetailDto(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genres = genres.map { it.toMovieGenreDto() },
        overview = overview,
        credits = credits.toCreditsResponseDto(),
    )
}
