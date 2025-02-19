package com.example.ratemovies.movie.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponseDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>,
)