package com.example.ratemovies.movie.domain.util

import com.example.ratemovies.movie.presentation.models.MovieUi
import kotlinx.serialization.Serializable

@Serializable
data class MovieNavArgs(
    val id: Int,
    val bannerUrl: String,
    val title: String,
    val imageUrl: String,
)

fun MovieUi.toMovieNavArgs(): MovieNavArgs {
    return MovieNavArgs(
        id = id,
        bannerUrl = banner,
        title = title,
        imageUrl = imageUrl,
    )
}