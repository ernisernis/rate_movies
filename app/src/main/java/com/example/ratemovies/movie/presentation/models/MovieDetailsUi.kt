package com.example.ratemovies.movie.presentation.models

import com.example.ratemovies.core.domain.util.round
import com.example.ratemovies.movie.domain.MovieDetails
import java.text.NumberFormat
import java.util.Locale

data class MovieDetailsUi(
    val id: Int,
    val releaseDate: String,
    val runtime: DisplayableValue,
    val voteAverage: String,
    val voteCount: DisplayableValue,
    val genres: List<MovieGenreUi>,
    val overview: String,
    val cast: List<CastUi>,
    val crew: List<CrewUi>,
    val director: String?,
    val writer: String?,
)

data class DisplayableValue(
    val value: Int,
    val formatted: String,
)

fun MovieDetails.toMovieDetailsUi(): MovieDetailsUi {
    return MovieDetailsUi(
        id = id,
        releaseDate = releaseDate,
        runtime = runtime.toDisplayableRuntime(),
        voteAverage = voteAverage.round(1).toString(),
        voteCount = voteCount.toDisplayableVoteCount(),
        genres = genres.map { it.toMovieGenreUi() },
        overview = overview,
        cast = cast.map { it.toCastUi() },
        crew = crew.map { it.toCrewUi() },
        director = crew.find { it.job == "Director" }?.name,
        writer = crew.find { it.job == "Screenplay" }?.name,
    )
}

fun Int.toDisplayableRuntime(): DisplayableValue {
    val hours = this / 60
    val mins = this % 60
    val formatted =
        if (hours > 0) {
            "${hours}h ${mins}min"
        } else {
            "${mins}min"
        }
    return DisplayableValue(
        value = this,
        formatted = formatted,
    )
}

fun Int.toDisplayableVoteCount(): DisplayableValue {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault())
    return DisplayableValue(
        value = this,
        formatted = formatter.format(this),
    )
}
