package com.example.ratemovies.movie.domain.model

data class Rating(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val runtimeFormatted: String?,
    val voteAverage: String,
    val description: String?,
    val userRating: Int,
    val creationTime: Long,
)


// For previews -->
internal val defaultRating = Rating(
    id = 0,
    posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
    title = "Terrifier 3",
    releaseDate = "2024.12.12",
    runtimeFormatted = "201555",
    voteAverage = "7.5",
    description = "Test Test test description! Cool movie!",
    userRating = 8,
    creationTime = 1707654321234,
)

internal fun generateDefaultRatings(count: Int): List<Rating> {
    return List(count) { index ->
       defaultRating.copy(
           id = index + 1,
           creationTime = System.currentTimeMillis()
       )
    }
}