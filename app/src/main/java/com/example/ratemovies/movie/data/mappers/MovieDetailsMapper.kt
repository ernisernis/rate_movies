package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.movie.data.networking.dto.CastDto
import com.example.ratemovies.movie.data.networking.dto.CrewDto
import com.example.ratemovies.movie.data.networking.dto.MovieDetailsDto
import com.example.ratemovies.movie.data.networking.dto.MovieGenreDto
import com.example.ratemovies.movie.domain.Cast
import com.example.ratemovies.movie.domain.Crew
import com.example.ratemovies.movie.domain.MovieDetails
import com.example.ratemovies.movie.domain.MovieGenre

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        releaseDate = release_date,
        runtime = runtime,
        voteAverage = vote_average,
        voteCount = vote_count,
        genres = genres.toMovieGenreList(),
        overview = overview,
        cast = credits.cast.toCastList().filter { it.profilePath != null },
        crew = credits.crew.toCrewList(),
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

fun List<CastDto>.toCastList(): List<Cast> {
    return this.map {
        Cast(
            id = it.id,
            name = it.name,
            profilePath = it.profile_path,
            character = it.character,
        )
    }
}

fun List<CrewDto>.toCrewList(): List<Crew> {
    return this.map {
        Crew(
            id = it.id,
            name = it.name,
            job = it.job,
            profilePath = it.profile_path ?: "",
        )
    }
}
