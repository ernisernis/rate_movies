package com.example.ratemovies.movie.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val character: String,
)
