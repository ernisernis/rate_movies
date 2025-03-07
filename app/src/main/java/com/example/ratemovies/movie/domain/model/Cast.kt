package com.example.ratemovies.movie.domain.model

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val character: String,
)
