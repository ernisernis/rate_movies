package com.example.ratemovies.movie.domain.model

data class CreditsResponse(
    val cast: List<Cast>,
    val crew: List<Crew>,
)
