package com.example.ratemovies.movie.domain.model

data class MovieDetail(
    val id: Int,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<MovieGenre>,
    val overview: String,
    val credits: CreditsResponse,
)

data class MovieGenre(
    val id: Int,
    val name: String,
)

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
)

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val character: String,
)

data class Crew(
    val id: Int,
    val name: String,
    val job: String,
    val profilePath: String,
)