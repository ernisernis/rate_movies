package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.movie.data.dto.CastDto
import com.example.ratemovies.movie.data.dto.CrewDto
import com.example.ratemovies.movie.data.dto.MovieDetailDto
import com.example.ratemovies.movie.data.dto.MovieGenreDto
import com.example.ratemovies.movie.domain.Cast
import com.example.ratemovies.movie.domain.Crew
import com.example.ratemovies.movie.domain.MovieDetail
import com.example.ratemovies.movie.domain.MovieGenre

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime,
        voteAverage = voteAverage,
        voteCount = voteCount,
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
            profilePath = it.profilePath,
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
            profilePath = it.profilePath ?: "",
        )
    }
}
