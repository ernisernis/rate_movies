package com.example.ratemovies.movie.domain

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
)
