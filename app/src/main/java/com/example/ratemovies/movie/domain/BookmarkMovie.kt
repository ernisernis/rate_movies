package com.example.ratemovies.movie.domain

data class BookmarkMovie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
    val creationTime: Long,
)

