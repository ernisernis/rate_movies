package com.example.ratemovies.movie.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CrewDto(
    val id: Int,
    val name: String,
    val job: String,
    val profile_path: String?,
)
