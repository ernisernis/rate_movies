package com.example.ratemovies.movie.presentation.models

import com.example.ratemovies.movie.domain.MovieDetails

data class MovieDetailsUi(
    val id: Int,
    val releaseDate: String,
    val runtime: DisplayableRuntime,
    val voteCount: Int,
)

data class DisplayableRuntime(
    val value: Int,
    val formatted: String,
)

fun MovieDetails.toMovieDetailsUi(): MovieDetailsUi {
    return MovieDetailsUi(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime.toDisplayableRuntime(),
        voteCount = voteCount,
    )
}

fun Int.toDisplayableRuntime(): DisplayableRuntime {
    val hours = this / 60
    val mins = this % 60
    val formatted =
        if (hours > 0) {
            "${hours}h ${mins}min"
        } else {
            "${mins}min"
        }
    return DisplayableRuntime(
        value = this,
        formatted = formatted,
    )
}
